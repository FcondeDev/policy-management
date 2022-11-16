package policy.management;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import policy.management.dto.*;
import policy.management.exception.CustomException;
import policy.management.util.DateStringConversion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class PolicyManagementIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;


    @Test
    @Order(1)
    void createPolicyTest() throws Exception {
        List<InsuredPersonDTO> insuredPersonsDTO = new ArrayList<>();
        String date = DateStringConversion.convertDateToString(LocalDate.now());
        insuredPersonsDTO.add(new InsuredPersonDTO(null, "Jane", "Johnson", 12.90));
        insuredPersonsDTO.add(new InsuredPersonDTO(null, "Jack", "Doe", 15.90));
        CreatePolicyDTO createPolicyDTO = new CreatePolicyDTO(date, insuredPersonsDTO);

        String body = mapper.writeValueAsString(createPolicyDTO);
        MvcResult result = mvc.perform(post("/policies").content(body).contentType(MediaType.APPLICATION_JSON)).andReturn();

        CreatePolicyResponseDTO responseDTO = mapper.readValue(result.getResponse().getContentAsString(), CreatePolicyResponseDTO.class);

        assertNotNull(responseDTO.getPolicyId());
        assertEquals(date, responseDTO.getStartDate());
        assertEquals(28.80, responseDTO.getTotalPremium());
        InsuredPersonDTO jane = responseDTO.getInsuredPersons().stream().filter(person -> person.getFirstName().equals("Jane")).findFirst().get();
        assertEquals("Johnson", jane.getSecondName());
        assertEquals(12.90, jane.getPremium());
        InsuredPersonDTO jack = responseDTO.getInsuredPersons().stream().filter(person -> person.getFirstName().equals("Jack")).findFirst().get();
        assertEquals("Doe", jack.getSecondName());
        assertEquals(15.90, jack.getPremium());

    }

    @Test
    @Order(2)
    void createPolicyTestWithWrongDate() throws Exception {
        List<InsuredPersonDTO> insuredPersonsDTO = new ArrayList<>();
        insuredPersonsDTO.add(new InsuredPersonDTO(null, "Jane", "Johnson", 12.90));
        insuredPersonsDTO.add(new InsuredPersonDTO(null, "Jack", "Doe", 15.90));
        CreatePolicyDTO createPolicyDTO = new CreatePolicyDTO("11.11.2020", insuredPersonsDTO);

        String body = mapper.writeValueAsString(createPolicyDTO);
        mvc.perform(post("/policies").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(result -> assertTrue(result.getResolvedException() instanceof CustomException));


    }

    @Test
    @Order(3)
    void updatePolicyTest() throws Exception {
        List<InsuredPersonDTO> insuredPersonsDTO = new ArrayList<>();
        String date = DateStringConversion.convertDateToString(LocalDate.now());
        insuredPersonsDTO.add(new InsuredPersonDTO(1L, "Jane", "Johnson", 12.90));
        insuredPersonsDTO.add(new InsuredPersonDTO(null, "Will", "Smith", 12.90));
        UpdatePolicyDTO updatePolicyDTO = new UpdatePolicyDTO(1L, date, insuredPersonsDTO);

        String body = mapper.writeValueAsString(updatePolicyDTO);
        MvcResult result = mvc.perform(put("/policies").content(body).contentType(MediaType.APPLICATION_JSON)).andReturn();

        UpdatePolicyResponseDTO responseDTO = mapper.readValue(result.getResponse().getContentAsString(), UpdatePolicyResponseDTO.class);

        assertNotNull(responseDTO.getPolicyId());
        assertEquals(2, responseDTO.getInsuredPersons().size());
        assertEquals(date, responseDTO.getEffectiveDate());
        assertEquals(25.80, responseDTO.getTotalPremium());
        InsuredPersonDTO jane = responseDTO.getInsuredPersons().stream().filter(person -> person.getFirstName().equals("Jane")).findFirst().get();
        assertEquals("Johnson", jane.getSecondName());
        assertEquals(12.90, jane.getPremium());
        InsuredPersonDTO will = responseDTO.getInsuredPersons().stream().filter(person -> person.getFirstName().equals("Will")).findFirst().get();
        assertEquals("Smith", will.getSecondName());
        assertEquals(12.90, will.getPremium());

    }

    @Test
    @Order(4)
    void getPolicyTest() throws Exception {
        String date = DateStringConversion.convertDateToString(LocalDate.now());

        MvcResult result = mvc.perform(get("/policies/id/{id}", 1).param("requestDate", date).contentType(MediaType.APPLICATION_JSON)).andReturn();

        GetPolicyResponseDTO responseDTO = mapper.readValue(result.getResponse().getContentAsString(), GetPolicyResponseDTO.class);

        assertNotNull(responseDTO.getPolicyId());
        assertEquals(2, responseDTO.getInsuredPersons().size());
        assertEquals(date, responseDTO.getRequestDate());
        assertEquals(25.80, responseDTO.getTotalPremium());
        InsuredPersonDTO jane = responseDTO.getInsuredPersons().stream().filter(person -> person.getFirstName().equals("Jane")).findFirst().get();
        assertEquals("Johnson", jane.getSecondName());
        assertEquals(12.90, jane.getPremium());
        InsuredPersonDTO will = responseDTO.getInsuredPersons().stream().filter(person -> person.getFirstName().equals("Will")).findFirst().get();
        assertEquals("Smith", will.getSecondName());
        assertEquals(12.90, will.getPremium());

    }

}
