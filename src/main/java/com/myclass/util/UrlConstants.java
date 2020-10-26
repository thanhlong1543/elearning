package com.myclass.util;

public class UrlConstants {
	
	private final static String ADMIN = "/admin/";
	private final static String CLIENT = "/client/";
	/*
	 * Url of admin
	 */
	public static class Admin{
		/**
		 * Request Mapping.
		 */
		public static final String HOME = ADMIN + "home";
		public static final String HOME_VIEW = ADMIN + "home/index";
		
		public static final String USER = ADMIN + "user";
		public static final String USER_VIEW = ADMIN + "user/index";
		public static final String USER_ADD = ADMIN + "user/add";
		public static final String USER_EDIT = ADMIN + "user/edit";
		public static final String USER_DELETE = ADMIN + "user/delete";
		
		public static final String ROLE = ADMIN + "role";
		public static final String ROLE_VIEW = ADMIN + "role/index";
		public static final String ROLE_ADD = ADMIN + "role/add";
		public static final String ROLE_EDIT = ADMIN + "role/edit";
		public static final String ROLE_DELETE = ADMIN + "role/delete";
		
		
		
		public static final String VIDEO = ADMIN + "video";
		public static final String VIDEO_VIEW = ADMIN + "video/index";
		public static final String VIDEO_ADD = ADMIN + "video/add";
		public static final String VIDEO_EDIT = ADMIN + "video/edit";
		public static final String VIDEO_DELETE = ADMIN + "video/delete";
		
		public static final String COURSE = ADMIN + "course";
		public static final String COURSE_VIEW = ADMIN + "course/index";
		public static final String COURSE_ADD = ADMIN + "course/add";
		public static final String COURSE_EDIT = ADMIN + "course/edit";
		public static final String COURSE_DELETE = ADMIN + "course/delete";
		
		public static final String TARGET = ADMIN + "target";
		public static final String TARGET_VIEW = ADMIN + "target/index";
		public static final String TARGET_ADD = ADMIN + "target/add";
		public static final String TARGET_EDIT = ADMIN + "target/edit";
		public static final String TARGET_DELETE = ADMIN + "target/delete";
		
		public static final String CATEGORY = ADMIN + "category";
		public static final String CATEGORY_VIEW = ADMIN + "category/index";
		public static final String CATEGORY_ADD = ADMIN + "category/add";
		public static final String CATEGORY_EDIT = ADMIN + "category/edit";
		public static final String CATEGORY_DELETE = ADMIN + "category/delete";
		
		public static final String ERROR_403 = ADMIN + "403";
		public static final String ERROR_VIEW_403 = ADMIN + "/error/403";
		
		// Link thymeleaf in Views
		public static final String FRAGMENTS_HEADER = "fragments/header";
		
		public static final String FILE_USER = "\\static\\adminStyle\\images\\users\\";
		public static final String FILE_COURSE = "\\static\\adminStyle\\images\\course\\";
		
		//APi
		public static final String LOGIN = "/login";
		public static final String API_AUTH = "/api/auth";
		public static final String API_USER = "/api/user";
		public static final String API_ROLE = "/api/role";
		public static final String API_CATEGORY = "/api/category";
	}
	
	public static class Client{
		public static final String HOME_URL = "/home";
		public static final String HOME = CLIENT + "home/index";
		
		public static final String COURSE_LIST = "/course/list";
		public static final String COURSE_DETAIL = "/course/detail";
		public static final String COURSE_SEARCH = "/course/search";
		public static final String COURSE_DETAIL_VIEW = CLIENT + "course/detail";
		public static final String COURSE_LIST_VIEW = CLIENT + "course/index";
		public static final String COURSE_SEARCH_VIEW = CLIENT + "course/search";
		
		public static final String ACCOUNT_LOGIN = "/account/login";
		public static final String ACCOUNT_REGISTER = "/account/register";
		public static final String ACCOUNT_SUCCESS = "/account/success";
		public static final String ACCOUNT_VIEW_LOGIN = CLIENT + "account/login";
		public static final String ACCOUNT_VIEW_SUCCESS = CLIENT + "account/success";
		public static final String ACCOUNT_VIEW_REGISTER = CLIENT + "account/register";
		
		public static final String API_CLIENT_COURSE = "/apiClient/course";
	}
}
