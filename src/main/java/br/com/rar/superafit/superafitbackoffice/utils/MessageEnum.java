package br.com.rar.superafit.superafitbackoffice.utils;

public enum MessageEnum {

	//General
	API_GENERIC_ERROR(MessageConstants.API_GENERIC_ERROR),
	API_MSG_UNAVAILABLE(MessageConstants.API_MSG_UNAVAILABLE),
	API_MSG_INTERNAL_SERVER_ERROR(MessageConstants.API_MSG_INTERNAL_SERVER_ERROR),

	//Schedule
	SCHEDULE_REQUIRED_WEEK_DAY(MessageConstants.SCHEDULE_REQUIRED_WEEK_DAY),
	SCHEDULE_MSG_SAVED_SUCCESS(MessageConstants.SCHEDULE_MSG_SAVED_SUCCESS),
	SCHEDULE_MSG_REMOVED_SUCCESS(MessageConstants.SCHEDULE_MSG_REMOVED_SUCCESS); 
	
	
	private final String msg;
	
	private MessageEnum(String msg) {
		this.msg = MessagesUtil.getInstance().get(msg);
	}
	
	public String getMsg() {
		return msg;
	}

	public interface MessageConstants {
		
		//General
		public static final String API_GENERIC_ERROR = "api_generic_error";
		public static final String API_MSG_UNAVAILABLE = "api_msg_unavailable";		
		public static final String API_MSG_INTERNAL_SERVER_ERROR = "api_msg_internal_server_error";

		//Schedule
		public static final String SCHEDULE_REQUIRED_WEEK_DAY = "schedule_required_week_day";
		public static final String SCHEDULE_REQUIRED_SCHEDULE_START = "schedule_required_schedule_start";
		public static final String SCHEDULE_REQUIRED_SCHEDULE_END = "schedule_required_schedule_end";
		public static final String SCHEDULE_MSG_SAVED_SUCCESS = "schedule_msg_saved_success";
		public static final String SCHEDULE_MSG_REMOVED_SUCCESS = "schedule_msg_removed_success";
		
	}
	
}
