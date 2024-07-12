package BuildWeekU5.EPIC.ENERGY.SERVICE;

import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.UtenteLoginPayload;
import BuildWeekU5.EPIC.ENERGY.SERVICE.payloads.UtentePayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc

class EpicEnergyServiceApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper mapper;

	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	private static String stringAsJSONStringify(final Object object) {
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (Exception e ) {
			throw new RuntimeException(e) ;
		}
	}

	@Test
	public void testRegistration() throws Exception {
	UtentePayload payload = new UtentePayload("Mohamed27", "momo273@gmail.com", "123456", "Mohamed", "Ali");
		this.mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:3001/auth/registration")
						.contentType(MediaType.APPLICATION_JSON)
						.content(stringAsJSONStringify(payload))).andDo(print())
				.andExpect(status().isCreated());

	}

	@Test
	public void testLogin() throws Exception {
		UtenteLoginPayload payload = new UtenteLoginPayload("momo27@gmail.com", "123456");
		this.mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:3001/auth/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content(stringAsJSONStringify(payload)))
				.andExpect(status().isOk());
	}

	@Test
	public void testGetFatture() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:3001/fatture")
						.param("page", "0")
						.param("size", "5")
						.param("sortBy", "id")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	void contextLoads() {
	}
}


