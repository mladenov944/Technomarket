package com.technomarket.users;

public abstract class IData {

	static boolean checkString(String str) {
		return ((str != null) && (str.trim().length() > 0)) ? true : false;
	}

	static boolean checkEMail(String mail) {
		if ((IData.checkString(mail)) && (mail.contains("@")) && (mail.contains("."))) {
			int indexQ = 0;
			int indexDot = 0;
			for (int i = 0; i < mail.length(); i++) {
				if (mail.charAt(i) == '@') {
					indexQ = i;
				}
				if (mail.charAt(i) == '.') {
					indexDot = i;
				}
			}
			if (indexQ < indexDot) {
				return true;
			} else {
				System.out.println("Vuveli ste nevaliden email");
				return false;
			}
		} else {
			System.out.println("Vuveli ste nevaliden email");
			return false;
		}
	}

	static boolean checkPhone(String phone) {
		boolean isValidNumber = true;
		if (!checkString(phone) && (phone.length() != 10) && !phone.startsWith("08")) {
			isValidNumber = false;
		} else {
			for (int i = 0; i < phone.length(); i++) {
				if (phone.charAt(i) < '0' || phone.charAt(i) > '9') {
					isValidNumber = false;
				}
			}
		}
		return isValidNumber;
	}
}
