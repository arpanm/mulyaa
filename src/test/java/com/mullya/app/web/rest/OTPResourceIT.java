package com.mullya.app.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mullya.app.IntegrationTest;
import com.mullya.app.domain.OTP;
import com.mullya.app.domain.enumeration.OtpStatus;
import com.mullya.app.domain.enumeration.OtpType;
import com.mullya.app.repository.OTPRepository;
import com.mullya.app.service.dto.OTPDTO;
import com.mullya.app.service.mapper.OTPMapper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link OTPResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OTPResourceIT {

    private static final String DEFAULT_OTP_VAL = "123456";
    private static final String UPDATED_OTP_VAL = "234567";

    private static final String DEFAULT_EMAIL = "abc@mail.com";
    private static final String UPDATED_EMAIL = "bcd@mail.com";

    private static final Long DEFAULT_PHONE = 1234567890l;
    private static final Long UPDATED_PHONE = 1234567891l;

    private static final OtpType DEFAULT_TYPE = OtpType.Email;
    private static final OtpType UPDATED_TYPE = OtpType.Phone;

    private static final LocalDateTime DEFAULT_EXPIRY_TIME = LocalDateTime.ofEpochSecond(0, 0, null);
    private static final LocalDateTime UPDATED_EXPIRY_TIME = LocalDateTime.now();

    private static final OtpStatus DEFAULT_STATUS = OtpStatus.Init;
    private static final OtpStatus UPDATED_STATUS = OtpStatus.Verified;

    private static final String ENTITY_API_URL = "/api/otps";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private OTPRepository oTPRepository;

    @Autowired
    private OTPMapper oTPMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOTPMockMvc;

    private OTP oTP;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OTP createEntity(EntityManager em) {
        OTP oTP = new OTP()
            .otpVal(DEFAULT_OTP_VAL)
            .email(DEFAULT_EMAIL)
            .phone(DEFAULT_PHONE)
            .type(DEFAULT_TYPE)
            .expiryTime(DEFAULT_EXPIRY_TIME)
            .status(DEFAULT_STATUS);
        return oTP;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OTP createUpdatedEntity(EntityManager em) {
        OTP oTP = new OTP()
            .otpVal(UPDATED_OTP_VAL)
            .email(UPDATED_EMAIL)
            .phone(UPDATED_PHONE)
            .type(UPDATED_TYPE)
            .expiryTime(UPDATED_EXPIRY_TIME)
            .status(UPDATED_STATUS);
        return oTP;
    }

    @BeforeEach
    public void initTest() {
        oTP = createEntity(em);
    }

    @Test
    @Transactional
    void createOTP() throws Exception {
        int databaseSizeBeforeCreate = oTPRepository.findAll().size();
        // Create the OTP
        OTPDTO oTPDTO = oTPMapper.toDto(oTP);
        restOTPMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(oTPDTO)))
            .andExpect(status().isCreated());

        // Validate the OTP in the database
        List<OTP> oTPList = oTPRepository.findAll();
        assertThat(oTPList).hasSize(databaseSizeBeforeCreate + 1);
        OTP testOTP = oTPList.get(oTPList.size() - 1);
        assertThat(testOTP.getOtpVal()).isEqualTo(DEFAULT_OTP_VAL);
        assertThat(testOTP.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testOTP.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testOTP.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testOTP.getExpiryTime()).isEqualTo(DEFAULT_EXPIRY_TIME);
        assertThat(testOTP.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    void createOTPWithExistingId() throws Exception {
        // Create the OTP with an existing ID
        oTP.setId(1L);
        OTPDTO oTPDTO = oTPMapper.toDto(oTP);

        int databaseSizeBeforeCreate = oTPRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOTPMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(oTPDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OTP in the database
        List<OTP> oTPList = oTPRepository.findAll();
        assertThat(oTPList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllOTPS() throws Exception {
        // Initialize the database
        oTPRepository.saveAndFlush(oTP);

        // Get all the oTPList
        restOTPMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(oTP.getId().intValue())))
            .andExpect(jsonPath("$.[*].otpVal").value(hasItem(DEFAULT_OTP_VAL)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].expiryTime").value(hasItem(DEFAULT_EXPIRY_TIME.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getOTP() throws Exception {
        // Initialize the database
        oTPRepository.saveAndFlush(oTP);

        // Get the oTP
        restOTPMockMvc
            .perform(get(ENTITY_API_URL_ID, oTP.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(oTP.getId().intValue()))
            .andExpect(jsonPath("$.otpVal").value(DEFAULT_OTP_VAL))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.expiryTime").value(DEFAULT_EXPIRY_TIME.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingOTP() throws Exception {
        // Get the oTP
        restOTPMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewOTP() throws Exception {
        // Initialize the database
        oTPRepository.saveAndFlush(oTP);

        int databaseSizeBeforeUpdate = oTPRepository.findAll().size();

        // Update the oTP
        OTP updatedOTP = oTPRepository.findById(oTP.getId()).get();
        // Disconnect from session so that the updates on updatedOTP are not directly saved in db
        em.detach(updatedOTP);
        updatedOTP
            .otpVal(UPDATED_OTP_VAL)
            .email(UPDATED_EMAIL)
            .phone(UPDATED_PHONE)
            .type(UPDATED_TYPE)
            .expiryTime(UPDATED_EXPIRY_TIME)
            .status(UPDATED_STATUS);
        OTPDTO oTPDTO = oTPMapper.toDto(updatedOTP);

        restOTPMockMvc
            .perform(
                put(ENTITY_API_URL_ID, oTPDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(oTPDTO))
            )
            .andExpect(status().isOk());

        // Validate the OTP in the database
        List<OTP> oTPList = oTPRepository.findAll();
        assertThat(oTPList).hasSize(databaseSizeBeforeUpdate);
        OTP testOTP = oTPList.get(oTPList.size() - 1);
        assertThat(testOTP.getOtpVal()).isEqualTo(UPDATED_OTP_VAL);
        assertThat(testOTP.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testOTP.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testOTP.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testOTP.getExpiryTime()).isEqualTo(UPDATED_EXPIRY_TIME);
        assertThat(testOTP.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void putNonExistingOTP() throws Exception {
        int databaseSizeBeforeUpdate = oTPRepository.findAll().size();
        oTP.setId(count.incrementAndGet());

        // Create the OTP
        OTPDTO oTPDTO = oTPMapper.toDto(oTP);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOTPMockMvc
            .perform(
                put(ENTITY_API_URL_ID, oTPDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(oTPDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OTP in the database
        List<OTP> oTPList = oTPRepository.findAll();
        assertThat(oTPList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOTP() throws Exception {
        int databaseSizeBeforeUpdate = oTPRepository.findAll().size();
        oTP.setId(count.incrementAndGet());

        // Create the OTP
        OTPDTO oTPDTO = oTPMapper.toDto(oTP);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOTPMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(oTPDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OTP in the database
        List<OTP> oTPList = oTPRepository.findAll();
        assertThat(oTPList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOTP() throws Exception {
        int databaseSizeBeforeUpdate = oTPRepository.findAll().size();
        oTP.setId(count.incrementAndGet());

        // Create the OTP
        OTPDTO oTPDTO = oTPMapper.toDto(oTP);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOTPMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(oTPDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the OTP in the database
        List<OTP> oTPList = oTPRepository.findAll();
        assertThat(oTPList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOTPWithPatch() throws Exception {
        // Initialize the database
        oTPRepository.saveAndFlush(oTP);

        int databaseSizeBeforeUpdate = oTPRepository.findAll().size();

        // Update the oTP using partial update
        OTP partialUpdatedOTP = new OTP();
        partialUpdatedOTP.setId(oTP.getId());

        partialUpdatedOTP.phone(UPDATED_PHONE).type(UPDATED_TYPE).expiryTime(UPDATED_EXPIRY_TIME);

        restOTPMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOTP.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOTP))
            )
            .andExpect(status().isOk());

        // Validate the OTP in the database
        List<OTP> oTPList = oTPRepository.findAll();
        assertThat(oTPList).hasSize(databaseSizeBeforeUpdate);
        OTP testOTP = oTPList.get(oTPList.size() - 1);
        assertThat(testOTP.getOtpVal()).isEqualTo(DEFAULT_OTP_VAL);
        assertThat(testOTP.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testOTP.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testOTP.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testOTP.getExpiryTime()).isEqualTo(UPDATED_EXPIRY_TIME);
        assertThat(testOTP.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    void fullUpdateOTPWithPatch() throws Exception {
        // Initialize the database
        oTPRepository.saveAndFlush(oTP);

        int databaseSizeBeforeUpdate = oTPRepository.findAll().size();

        // Update the oTP using partial update
        OTP partialUpdatedOTP = new OTP();
        partialUpdatedOTP.setId(oTP.getId());

        partialUpdatedOTP
            .otpVal(UPDATED_OTP_VAL)
            .email(UPDATED_EMAIL)
            .phone(UPDATED_PHONE)
            .type(UPDATED_TYPE)
            .expiryTime(UPDATED_EXPIRY_TIME)
            .status(UPDATED_STATUS);

        restOTPMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOTP.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOTP))
            )
            .andExpect(status().isOk());

        // Validate the OTP in the database
        List<OTP> oTPList = oTPRepository.findAll();
        assertThat(oTPList).hasSize(databaseSizeBeforeUpdate);
        OTP testOTP = oTPList.get(oTPList.size() - 1);
        assertThat(testOTP.getOtpVal()).isEqualTo(UPDATED_OTP_VAL);
        assertThat(testOTP.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testOTP.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testOTP.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testOTP.getExpiryTime()).isEqualTo(UPDATED_EXPIRY_TIME);
        assertThat(testOTP.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void patchNonExistingOTP() throws Exception {
        int databaseSizeBeforeUpdate = oTPRepository.findAll().size();
        oTP.setId(count.incrementAndGet());

        // Create the OTP
        OTPDTO oTPDTO = oTPMapper.toDto(oTP);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOTPMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, oTPDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(oTPDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OTP in the database
        List<OTP> oTPList = oTPRepository.findAll();
        assertThat(oTPList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOTP() throws Exception {
        int databaseSizeBeforeUpdate = oTPRepository.findAll().size();
        oTP.setId(count.incrementAndGet());

        // Create the OTP
        OTPDTO oTPDTO = oTPMapper.toDto(oTP);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOTPMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(oTPDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OTP in the database
        List<OTP> oTPList = oTPRepository.findAll();
        assertThat(oTPList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOTP() throws Exception {
        int databaseSizeBeforeUpdate = oTPRepository.findAll().size();
        oTP.setId(count.incrementAndGet());

        // Create the OTP
        OTPDTO oTPDTO = oTPMapper.toDto(oTP);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOTPMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(oTPDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the OTP in the database
        List<OTP> oTPList = oTPRepository.findAll();
        assertThat(oTPList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOTP() throws Exception {
        // Initialize the database
        oTPRepository.saveAndFlush(oTP);

        int databaseSizeBeforeDelete = oTPRepository.findAll().size();

        // Delete the oTP
        restOTPMockMvc.perform(delete(ENTITY_API_URL_ID, oTP.getId()).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OTP> oTPList = oTPRepository.findAll();
        assertThat(oTPList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
