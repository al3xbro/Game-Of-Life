package test;
import java.awt.Color;

public class Tester {
	public static void main (String[] args) {
		Color original = Color.DARK_GRAY;
		Color lighter = original.brighter().brighter();
		System.out.println(original.getAlpha() + " " + original.getRed() + " " + original.getGreen() + " " + original.getBlue());
		System.out.println(lighter.getRed() + " " + lighter.getGreen() + " " + lighter.getBlue());
	}
}
