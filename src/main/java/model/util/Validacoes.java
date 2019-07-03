package model.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validacoes {

	
	
	// 1
	// OBS: So funcionou recebendo tipo String, não consegui recebendo tipo Data e convertendo para string
	
	
	public static boolean VerificaData(String data) {
		boolean verifica = true;
		
		if (validaData(data) == true) {
	        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        LocalDate dataVerificada = LocalDate.parse(data, dtf);
	        LocalDate hoje = LocalDate.now();
	        if (dataVerificada.compareTo(hoje) <= 0) {
	        	verifica = false;
	        }
		}
        return verifica;
    }
	
	public static boolean validaData(String data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		try {
			sdf.parse(data);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	// 2
	public static boolean verificaString(String s) {
		boolean verifica = true;
		String palavra = s.replaceAll(" ", "");
		for (int i = 0; i < palavra.length(); i++) {
			if (Character.isLetter(palavra.charAt(i)) == false) {
				verifica = false;
				break;
			}
		}
		return verifica;
	}

	
	// 3
	// OBS: O Telefone tem que vim dessa forma "(87) 98120-3164"
	public static boolean verificaTelefone(String numeroTelefone) {
		return numeroTelefone.matches(".((10)|([1-9][1-9]).)\\s9?[6-9][0-9]{3}-[0-9]{4}")
				|| numeroTelefone.matches(".((10)|([1-9][1-9]).)\\s[2-5][0-9]{3}-[0-9]{4}");
	}

	
	// 4
	public static boolean verificaValor(double valor) {
		boolean verifica = true;
		if (valor == 0.0 || valor < 0.0) {
			verifica = false;
		}
		return verifica;
	}

	
	//5
	// OBS: O CPF tem que vim dessa forma "12041918480"
	public static boolean verificaCpf(String CPF) {
		boolean verifica = true;
		if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222")
				|| CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
				|| CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
				|| CPF.equals("99999999999") || (CPF.length() != 11)) {
			verifica = false;
		}

		if (verifica == true) {
			char dig10, dig11;
			int soma, resultado, numero, peso;

			soma = 0;
			peso = 10;

			for (int i = 0; i < 9; i++) {
				numero = (int) (CPF.charAt(i) - 48);
				soma = soma + (numero * peso);
				peso = peso - 1;
			}

			resultado = 11 - (soma % 11);
			if ((resultado == 10) || (resultado == 11)) {
				dig10 = '0';
			} else {
				dig10 = (char) (resultado + 48);
			}

			soma = 0;
			peso = 11;

			for (int i = 0; i < 10; i++) {
				numero = (int) (CPF.charAt(i) - 48);
				soma = soma + (numero * peso);
				peso = peso - 1;
			}

			resultado = 11 - (soma % 11);
			if ((resultado == 10) || (resultado == 11)) {
				dig11 = '0';
			} else {
				dig11 = (char) (resultado + 48);
			}

			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
				verifica = true;
			} else {
				verifica = false;
			}
		}
		return verifica;
	}
	
	
	//7
	public static boolean verificaEmail(String email) { 
		boolean verifica = false;
	    Pattern pattern = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");   
	    Matcher matcher = pattern.matcher(email);   
	    if (matcher.find() == true) {
	    	verifica = true;
	    }
	    return verifica;
	}
	
}
