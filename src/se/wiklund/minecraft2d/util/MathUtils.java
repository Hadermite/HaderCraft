package se.wiklund.minecraft2d.util;

public class MathUtils {
	
	public static double clamp(double variable, double min, double max) {
		if (variable < min) return min;
		if (variable > max) return max;
		return variable;
	}
	
	public static double encounterZero(double variable, double amount) {
		if (variable > 0) {
			variable -= amount;
			if (variable < 0) return 0;
		} else if (variable < 0) {
			variable += amount;
			if (variable > 0) return 0;
		} else {
			return 0;
		}
		return variable;
	}
}
