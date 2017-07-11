package com.welloculus.apigateway.constants;

public final class Constants {

	private Constants() {
	}

	public static final String ERRORMESSAGE = "errorMessage";
	public static final String SUCCESS = "success";
	public static final String RESPONSE_CODE = "responseCode";
	public static final String SUCCESSCODE = "200";
	public static final String YES = "YES";
	public static final String NO = "N";

	public static final String JSON_CONTENT = "application/json";
	public static final String ACCEPT = "accept";
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String RESPONSE_CONTENT = "responseContent";

	public static final class ApiPath {
		private ApiPath() {
		}

		public static final String ID = "id";
		public static final String PATIENTS = "/ApiGateway/patients";
		public static final String DEVICES = "/ApiGateway/devices";
		public static final String GET_PATIENTS = "/getPatients";
		public static final String GET_PATIENT_BY_ID = "/getPatient/{id}";
		public static final String UPDATE_PATIENT_BY_ID = "/updatePatient/{id}";
		public static final String DELETE_PATIENT_BY_ID = "/deletePatient/{id}";
		public static final String ADD_PATIENT = "/addPatient";
		public static final String GET_DEVICES = "/getDevices";
		public static final String GET_DEVICE_BY_ID = "/getDevice/{id}";
		public static final String UPDATE_DEVICE_BY_ID = "/updateDevice/{id}";
		public static final String DELETE_DEVICE_BY_ID = "/deleteDevice/{id}";
		public static final String ADD_DEVICE = "/addDevice";
	}
}
