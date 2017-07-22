package br.com.rar.superafit.superafitbackoffice.utils;

public enum MessageEnum {

	//General
	API_GENERIC_ERROR(MessageConstants.API_GENERIC_ERROR),
	API_MSG_UNAVAILABLE(MessageConstants.API_MSG_UNAVAILABLE),
	API_MSG_INTERNAL_SERVER_ERROR(MessageConstants.API_MSG_INTERNAL_SERVER_ERROR),
	API_MSG_UNAUTHORIZED(MessageConstants.API_MSG_UNAUTHORIZED),
	API_MSG_JWT_TOKEN_EXPIRED(MessageConstants.API_MSG_JWT_TOKEN_EXPIRED),
	API_UNMAPPED_ERROR(MessageConstants.API_UNMAPPED_ERROR),

	//Schedule
	SCHEDULE_REQUIRED_WEEK_DAY(MessageConstants.SCHEDULE_REQUIRED_WEEK_DAY),
	SCHEDULE_MSG_SAVED_SUCCESS(MessageConstants.SCHEDULE_MSG_SAVED_SUCCESS),
	SCHEDULE_MSG_REMOVED_SUCCESS(MessageConstants.SCHEDULE_MSG_REMOVED_SUCCESS),
	SCHEDULE_MSG_REMIDER_PUBLISH(MessageConstants.SCHEDULE_MSG_REMIDER_PUBLISH), 
	SCHEDULE_MSG_PUBLISHED(MessageConstants.SCHEDULE_MSG_PUBLISHED), 
	SCHEDULE_MSG_NOT_FOUND(MessageConstants.SCHEDULE_MSG_NOT_FOUND), 
	
	//Trainning
	CREATE_TRAINNING_SUCCESS(MessageConstants.CREATE_TRAINNING_SUCCESS), 
	DELETE_TRAINNING_SUCCESS(MessageConstants.DELETE_TRAINNING_SUCCESS), 
	TRAINNING_MSG_PUBLISHED(MessageConstants.TRAINNING_MSG_PUBLISHED), 
	TRAINNING_MSG_NOT_FOUND(MessageConstants.TRAINNING_MSG_NOT_FOUND),
	TRAINNING_MSG_REMIDER_PUBLISH(MessageConstants.TRAINNING_MSG_REMIDER_PUBLISH), 
	UPDATE_TRAINNING_SUCCESS(MessageConstants.UPDATE_TRAINNING_SUCCESS); 
	
	
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
		public static final String API_MSG_UNAUTHORIZED = "api_message_unauthorized";
		public static final String API_MSG_JWT_TOKEN_EXPIRED = "api_message_jwt_token_expired";
		public static final String API_UNMAPPED_ERROR = "api_unmapped_error";

		//Schedule
		public static final String SCHEDULE_REQUIRED_WEEK_DAY = "schedule_required_week_day";
		public static final String SCHEDULE_REQUIRED_SCHEDULE_START = "schedule_required_schedule_start";
		public static final String SCHEDULE_REQUIRED_SCHEDULE_END = "schedule_required_schedule_end";
		public static final String SCHEDULE_MSG_SAVED_SUCCESS = "schedule_msg_saved_success";
		public static final String SCHEDULE_MSG_REMOVED_SUCCESS = "schedule_msg_removed_success";
		public static final String SCHEDULE_MSG_REMIDER_PUBLISH = "schedule_msg_reminder_publish";
		public static final String SCHEDULE_MSG_PUBLISHED = "schedule_msg_published";
		public static final String SCHEDULE_MSG_NOT_FOUND = "schedule_msg_not_found";
		
		//Trainning
		public static final String CREATE_TRAINNING_SUCCESS = "create_trainning_success";
		public static final String DELETE_TRAINNING_SUCCESS = "delete_trainning_success";;
		public static final String TRAINNING_MSG_PUBLISHED = "trainning_msg_published";
		public static final String TRAINNING_MSG_REMIDER_PUBLISH = "trainning_msg_reminder_publish";
		public static final String TRAINNING_MSG_NOT_FOUND = "trainning_msg_not_found";
		public static final String UPDATE_TRAINNING_SUCCESS = "update_trainning_success";
		
	}
	
}
