import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IUser } from 'app/shared/model/user.model';
import { getEntities as getUsers } from 'app/entities/user/user.reducer';
import { getEntity, updateEntity, createEntity, reset } from './otp.reducer';
import { IOTP } from 'app/shared/model/otp.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { OtpType } from 'app/shared/model/enumerations/otp-type.model';
import { OtpStatus } from 'app/shared/model/enumerations/otp-status.model';

export const OTPUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const users = useAppSelector(state => state.user.entities);
  const oTPEntity = useAppSelector(state => state.oTP.entity);
  const loading = useAppSelector(state => state.oTP.loading);
  const updating = useAppSelector(state => state.oTP.updating);
  const updateSuccess = useAppSelector(state => state.oTP.updateSuccess);
  const otpTypeValues = Object.keys(OtpType);
  const otpStatusValues = Object.keys(OtpStatus);
  const handleClose = () => {
    props.history.push('/otp');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(props.match.params.id));
    }

    dispatch(getUsers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...oTPEntity,
      ...values,
      user: users.find(it => it.id.toString() === values.user.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          type: 'Email',
          status: 'Init',
          ...oTPEntity,
          user: oTPEntity?.user?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="mulyaaApp.oTP.home.createOrEditLabel" data-cy="OTPCreateUpdateHeading">
            <Translate contentKey="mulyaaApp.oTP.home.createOrEditLabel">Create or edit a OTP</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="otp-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField label={translate('mulyaaApp.oTP.otpVal')} id="otp-otpVal" name="otpVal" data-cy="otpVal" type="text" />
              <ValidatedField label={translate('mulyaaApp.oTP.email')} id="otp-email" name="email" data-cy="email" type="text" />
              <ValidatedField label={translate('mulyaaApp.oTP.phone')} id="otp-phone" name="phone" data-cy="phone" type="text" />
              <ValidatedField label={translate('mulyaaApp.oTP.type')} id="otp-type" name="type" data-cy="type" type="select">
                {otpTypeValues.map(otpType => (
                  <option value={otpType} key={otpType}>
                    {translate('mulyaaApp.OtpType.' + otpType)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('mulyaaApp.oTP.expiryTime')}
                id="otp-expiryTime"
                name="expiryTime"
                data-cy="expiryTime"
                type="date"
              />
              <ValidatedField label={translate('mulyaaApp.oTP.status')} id="otp-status" name="status" data-cy="status" type="select">
                {otpStatusValues.map(otpStatus => (
                  <option value={otpStatus} key={otpStatus}>
                    {translate('mulyaaApp.OtpStatus.' + otpStatus)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('mulyaaApp.oTP.createdOn')}
                id="otp-createdOn"
                name="createdOn"
                data-cy="createdOn"
                type="date"
              />
              <ValidatedField
                label={translate('mulyaaApp.oTP.createdBy')}
                id="otp-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('mulyaaApp.oTP.updatedOn')}
                id="otp-updatedOn"
                name="updatedOn"
                data-cy="updatedOn"
                type="date"
              />
              <ValidatedField
                label={translate('mulyaaApp.oTP.updatedBy')}
                id="otp-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField id="otp-user" name="user" data-cy="user" label={translate('mulyaaApp.oTP.user')} type="select">
                <option value="" key="0" />
                {users
                  ? users.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/otp" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default OTPUpdate;
