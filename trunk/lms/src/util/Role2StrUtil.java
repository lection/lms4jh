package util;

import lms.model.LmsUser;

public class Role2StrUtil {
	public static String toString(int roleId){
		switch(roleId){
		case LmsUser.STUDENT:
			return "在校学生";
		case LmsUser.SCHOOLFELLOW:
			return "校友";
		case LmsUser.AUDITING:
			return "待审核";
		default:
			return "管理员";
		}
	}
}
