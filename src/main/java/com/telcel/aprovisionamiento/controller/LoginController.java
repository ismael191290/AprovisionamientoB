package com.telcel.aprovisionamiento.controller;

import java.security.Principal;
import java.util.Calendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.telcel.aprovisionamiento.exceptions.RequestExceptions;



@Controller
public class LoginController {

	private Logger loggin = LoggerFactory.getLogger(LoginController.class);
	
	@GetMapping({ "", "/", "/login" })
	public String login(@RequestParam(value = "error", required = false) String error,
	    @RequestParam(value = "logout", required = false) String logout, Model model, Principal principal,
	    RedirectAttributes flAttributes) {
		try {
			loggin.info("<<<<<< Logeo >>>>>");
			if (principal != null) {
				flAttributes.addFlashAttribute("SesionInfo", "Cuentas con una Sesion");
				flAttributes.addFlashAttribute("name", principal.getName());
				loggin.info("El Usuario '"+principal.getName()+"' Tiene session activa");
				return "redirect:/inicio";
			}
	
			if (error != null) {
				model.addAttribute("error", "Usuario o Contraseña incorrecta intenta de nuevo");
				loggin.error("Error: Usuario o Contraseña incorrecta Intenta de nuevo.");
			}
	
			if (logout != null) {
				model.addAttribute("success", "Vuelve Pronto");
				loggin.info("Cerro Session el Usuario ");
			}
		} catch (Exception e) {
			throw new RequestExceptions("Error: Logeo", e);
		}
		return "login";
	}

	@GetMapping("/inicio")
	public String inicio(Model model) {
		try {
			Calendar c = Calendar.getInstance();
			String dia = Integer.toString(c.get(Calendar.DATE));
			String mes = (Integer.toString(c.get(Calendar.MONTH) + 1));
			String annio = Integer.toString(c.get(Calendar.YEAR));
			String fc = dia + "-" + mes + "-" + annio;
			model.addAttribute("time", fc);
			model.addAttribute("user", "Admin");
			return "aprovisionamiento/aprovisionamiento";
		}catch (Exception e) {
			throw new RequestExceptions("Error: Inicio Sistema", e);
		}
	}
}
