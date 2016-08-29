package me.gacl.url;

public interface Url {

	public static final String USER_LOGIN_PAGE = BaseUrl.USER + "/login";

	public static final String USER_DOLOGIN = BaseUrl.USER + "/doLogin";

	public static final String USER_REGISTER = BaseUrl.USER + "/register";

	public static class MustLogin{
		
		public static final String USER_DETAIL = BaseUrl.USER + "/detail";
		
		public static final String SHOP_NEWS = BaseUrl.SHOP + "/news";
		
	}
}
