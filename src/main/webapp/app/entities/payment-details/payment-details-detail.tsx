import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './payment-details.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const PaymentDetailsDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const paymentDetailsEntity = useAppSelector(state => state.paymentDetails.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="paymentDetailsDetailsHeading">
          <Translate contentKey="mulyaaApp.paymentDetails.detail.title">PaymentDetails</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{paymentDetailsEntity.id}</dd>
          <dt>
            <span id="amount">
              <Translate contentKey="mulyaaApp.paymentDetails.amount">Amount</Translate>
            </span>
          </dt>
          <dd>{paymentDetailsEntity.amount}</dd>
          <dt>
            <span id="paymentType">
              <Translate contentKey="mulyaaApp.paymentDetails.paymentType">Payment Type</Translate>
            </span>
          </dt>
          <dd>{paymentDetailsEntity.paymentType}</dd>
          <dt>
            <span id="onlinePgType">
              <Translate contentKey="mulyaaApp.paymentDetails.onlinePgType">Online Pg Type</Translate>
            </span>
          </dt>
          <dd>{paymentDetailsEntity.onlinePgType}</dd>
          <dt>
            <span id="pgTxnId">
              <Translate contentKey="mulyaaApp.paymentDetails.pgTxnId">Pg Txn Id</Translate>
            </span>
          </dt>
          <dd>{paymentDetailsEntity.pgTxnId}</dd>
          <dt>
            <span id="pgStatus">
              <Translate contentKey="mulyaaApp.paymentDetails.pgStatus">Pg Status</Translate>
            </span>
          </dt>
          <dd>{paymentDetailsEntity.pgStatus}</dd>
          <dt>
            <span id="offlineTxnId">
              <Translate contentKey="mulyaaApp.paymentDetails.offlineTxnId">Offline Txn Id</Translate>
            </span>
          </dt>
          <dd>{paymentDetailsEntity.offlineTxnId}</dd>
          <dt>
            <span id="offlineTxnDetails">
              <Translate contentKey="mulyaaApp.paymentDetails.offlineTxnDetails">Offline Txn Details</Translate>
            </span>
          </dt>
          <dd>{paymentDetailsEntity.offlineTxnDetails}</dd>
          <dt>
            <span id="offlineTxnCollectedBy">
              <Translate contentKey="mulyaaApp.paymentDetails.offlineTxnCollectedBy">Offline Txn Collected By</Translate>
            </span>
          </dt>
          <dd>{paymentDetailsEntity.offlineTxnCollectedBy}</dd>
          <dt>
            <span id="offlineTxnClearingStatus">
              <Translate contentKey="mulyaaApp.paymentDetails.offlineTxnClearingStatus">Offline Txn Clearing Status</Translate>
            </span>
          </dt>
          <dd>{paymentDetailsEntity.offlineTxnClearingStatus}</dd>
          <dt>
            <span id="paymentDate">
              <Translate contentKey="mulyaaApp.paymentDetails.paymentDate">Payment Date</Translate>
            </span>
          </dt>
          <dd>{paymentDetailsEntity.paymentDate}</dd>
          <dt>
            <span id="paymentInitTime">
              <Translate contentKey="mulyaaApp.paymentDetails.paymentInitTime">Payment Init Time</Translate>
            </span>
          </dt>
          <dd>
            {paymentDetailsEntity.paymentInitTime ? (
              <TextFormat value={paymentDetailsEntity.paymentInitTime} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="paymentUpdateTime">
              <Translate contentKey="mulyaaApp.paymentDetails.paymentUpdateTime">Payment Update Time</Translate>
            </span>
          </dt>
          <dd>
            {paymentDetailsEntity.paymentUpdateTime ? (
              <TextFormat value={paymentDetailsEntity.paymentUpdateTime} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="paymentStatus">
              <Translate contentKey="mulyaaApp.paymentDetails.paymentStatus">Payment Status</Translate>
            </span>
          </dt>
          <dd>{paymentDetailsEntity.paymentStatus}</dd>
          <dt>
            <span id="createdOn">
              <Translate contentKey="mulyaaApp.paymentDetails.createdOn">Created On</Translate>
            </span>
          </dt>
          <dd>
            {paymentDetailsEntity.createdOn ? (
              <TextFormat value={paymentDetailsEntity.createdOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="mulyaaApp.paymentDetails.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{paymentDetailsEntity.createdBy}</dd>
          <dt>
            <span id="updatedOn">
              <Translate contentKey="mulyaaApp.paymentDetails.updatedOn">Updated On</Translate>
            </span>
          </dt>
          <dd>
            {paymentDetailsEntity.updatedOn ? (
              <TextFormat value={paymentDetailsEntity.updatedOn} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="mulyaaApp.paymentDetails.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{paymentDetailsEntity.updatedBy}</dd>
          <dt>
            <Translate contentKey="mulyaaApp.paymentDetails.order">Order</Translate>
          </dt>
          <dd>{paymentDetailsEntity.order ? paymentDetailsEntity.order.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/payment-details" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/payment-details/${paymentDetailsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default PaymentDetailsDetail;
