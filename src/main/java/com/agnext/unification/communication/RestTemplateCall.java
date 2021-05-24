package com.agnext.unification.communication;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.agnext.unification.model.CustomerCommodityAssignmentModel;

@Component
public class RestTemplateCall {

    //	public static void main(String args[]) {
    //		CustomerCommodityAssignmentModel ccAM = new CustomerCommodityAssignmentModel();
    //		Long [] comCatIds= {1L};
    //		
    //		Optional.ofNullable(comCatIds).ifPresent(ccAM::setCommodityCategoryId);
    //		Optional.ofNullable(1L).ifPresent(ccAM::setCustomerId);
    //		Optional.ofNullable(1592222110L).ifPresent(ccAM::setStartOfSubscription);
    //		Optional.ofNullable(1593518110L).ifPresent(ccAM::setEndOfSubscription);
    ////		Optional.ofNullable(request.getProductId()).ifPresent(ccAM::setProductId);
    //		
    //		String token="eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2VtYWlsIjoidmlzaGFsYmFuc2FsQGFnbmV4dC5pbiIsInVzZXJfZm5hbWUiOiJWaXNoYWwiLCJ1c2VyX25hbWUiOiJ2aXNoYWxiYW5zYWxAYWduZXh0LmluIiwiY3VzdG9tZXJfdXVpZCI6ImYwMWU1OWQzLWVkYTgtNDM3Zi04ZDg5LWVmNGM5M2VlNzI4MSIsInJvbGVzIjpbImFkbWluIl0sImlzcyI6IlF1YWxpeCIsInVzZXJfbG5hbWUiOiJCYW5zYWwiLCJjbGllbnRfaWQiOiJjbGllbnRJZCIsInVzZXJfdXVpZCI6bnVsbCwidXNlcl90eXBlIjoiU0VSVklDRV9QUk9WSURFUiIsInVzZXJfaWQiOjE2LCJ1c2VyX21vYmlsZSI6Ijg5ODk4OTg5MTYiLCJzY29wZSI6WyJhbGwiXSwidXNlcl9oaWVyYXJjaHkiOm51bGwsImN1c3RvbWVyX25hbWUiOiJBZ25leHQgUHZ0LiBMdGQuIiwiZXhwIjoxNTk3MjY2OTU0LCJjdXN0b21lcl9pZCI6MSwianRpIjoiZWI0ZTRkZGYtMjM2Ni00OGU4LTkyNjgtM2E3MGY4OGU3NTU4In0.faeVgGg5poJJR2VGdYuDIXmPhfi-XV4UKlLzcpFzs4GcdoivL6Bd6UKz-qMZy8rP7FUWdKCy3ZAm8PTAShSjM9MER4pKlB9KgGQZ9yrmENlDz0_IIEl93_h83AVCQgokLEVj4OkBlrb1SW90Y0O48BA4Hdi5OCT-aHpd3MCsinCcIyEqzpH1cNFieAiksygK5lTTUEHdWdQBAz3wPKCgxFxLx9JeDaQvULgcpn1ydtYLQTfUcfx6kOJuKbJSALx0luAgFHWYab7jNSqtmbs1bxtOcfoHmejpSEfIUc55ktRiagynGyKQ-kp668jT0mjSpgPIbJBcPA-pf6g6B-CZtQ";
    //		customerCommAssignment( ccAM,  token);
    //	}

    public static final String hostName = "http://13.71.36.247:9991/";

    public String customerCommAssignment(CustomerCommodityAssignmentModel ccAM, String token) {
	String uri = hostName + "api/customer/commodity/assignment";
	System.out.println("ccAM : " + ccAM);
	HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);
	headers.set("Authorization", "Bearer " + token);

	//		HttpEntity entity = new HttpEntity(headers);
	HttpEntity<CustomerCommodityAssignmentModel> request = new HttpEntity<>(ccAM, headers);
	RestTemplate restTemplate = new RestTemplate();
	String result = restTemplate.postForObject(uri, request, String.class);
	System.out.println("****************  " + result);
	return result;
    }

    public Long[] getCommodityCategoriesByCustomerId(Long customerId, String token) {
	String uri = hostName + "api/customer/commodity/assigned/{customer_id}";

	//HttpEntity entity = new HttpEntity(headers);
	RestTemplate restTemplate = new RestTemplate();
	HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);
	headers.set("Authorization", "Bearer " + token);

	HttpEntity entity = new HttpEntity(headers);
	Map<String, Long> params = new HashMap<String, Long>();
	params.put("customer_id", customerId);
	ResponseEntity<Long[]> result = restTemplate.exchange(uri, HttpMethod.GET, entity, Long[].class, params);

	return result.getBody();
    }

    public String getCustomerName(Long customerId, String token) {
	String uri = "http://13.71.36.247:9991/api/customer/name/{customer_id}";

	// HttpEntity entity = new HttpEntity(headers);
	RestTemplate restTemplate = new RestTemplate();
	HttpHeaders headers = new HttpHeaders();
	headers.setContentType(MediaType.APPLICATION_JSON);
	headers.set("Authorization", "Bearer "
		+ "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2VtYWlsIjoibWl0ZXNocGFzc2lAYWduZXh0LmluIiwidXNlcl9mbmFtZSI6Im1pdGVzaCIsInVzZXJfbmFtZSI6Im1pdGVzaHBhc3NpQGFnbmV4dC5pbiIsImN1c3RvbWVyX3V1aWQiOiJmMDFlNTlkMy1lZGE4LTQzN2YtOGQ4OS1lZjRjOTNlZTcyODEiLCJyb2xlcyI6WyJhZG1pbiJdLCJpc3MiOiJRdWFsaXgiLCJ1c2VyX2xuYW1lIjoicGFzc2kiLCJjbGllbnRfaWQiOiJjbGllbnRJZCIsInVzZXJfdXVpZCI6bnVsbCwidXNlcl90eXBlIjoiU0VSVklDRV9QUk9WSURFUiIsInVzZXJfaWQiOjE2LCJ1c2VyX21vYmlsZSI6Ijg5ODk4OTg5MTYiLCJzY29wZSI6WyJhbGwiXSwidXNlcl9oaWVyYXJjaHkiOm51bGwsImN1c3RvbWVyX25hbWUiOiJBZ25leHQgUHZ0LiBMdGQuIiwiZXhwIjoxNjA1NTk3ODM1LCJjdXN0b21lcl9pZCI6MSwianRpIjoiNzVkNTM4ZjAtZDBiYi00NjEzLWEzY2ItYWI1YTZmMzNlYzJlIn0.SbAdfezg-UXSmULQ1zXVX7zLrAyNHvyfoz_jhgnlMeSK_1_fl7TKhTez9McWwLXQMgbbhTIrKNVwQk9K7y0xlmRK3bQBpqJyJa30yKfk6pn2hoR0tVA-mpEY15vwaNN0B6mhtMJ_hrK0Wr2Kd_JMs1HHNojCFPBDkxgt6JfhomhJYfDlzO6JskOJi_JPC0HD6qfcNM87wFUO3e8eiDmBEasHhV27bh-8Wtt_kV2Ay-cHFYHV_48o7KuFVxc9zVkVaODpf4bk5ZtW5iIcXwNe4O42wsOnpwWwZs3Q-5aPaj0xn_06Kx2k7PP7M4cl8y3H-_SM15SSj8R8jqdx93ZpFA");

	HttpEntity entity = new HttpEntity(headers);
	Map<String, Long> params = new HashMap<String, Long>();
	params.put("customer_id", customerId);
	ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class, params);

	return result.getBody();
    }

}
