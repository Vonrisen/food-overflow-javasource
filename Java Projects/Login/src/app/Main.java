package app;
import controllers.LoginController;
import utilities.IstatUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
	
	public static void main(String[] args) {
		
		LoginController login_controller = new LoginController();
		login_controller.openLoginFrame();
}
}
