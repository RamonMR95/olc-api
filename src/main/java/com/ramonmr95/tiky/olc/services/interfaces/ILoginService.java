package com.ramonmr95.tiky.olc.services.interfaces;

import java.util.Map;

public interface ILoginService {

	public Map<String, String> login(String username, String password);
}
