import java.util.Scanner;

import javax.swing.CellEditor;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		String order = null;
		int exNum = 0, valueNum = 1;
		
		while (valueNum <= 1) {
			System.out.println("�������1. ʹ�� -n ��������������Ŀ�ĸ���     2. ʹ�� -r ����������Ŀ����ֵ����Ȼ������������������ĸ���ķ�Χ");
			Scanner scanner = new Scanner(System.in);
			if (scanner.hasNext()) {
				order = scanner.next();
			}
			if (scanner.hasNextLine()) {
				if (order.equals("-n")) {
					exNum = scanner.nextInt();
				} else if (order.equals("-r")) {
					valueNum = scanner.nextInt();
				} else {
					System.out.println("��������ȷ�����������1. ʹ�� -n ��������������Ŀ�ĸ���     2. ʹ�� -r ����������Ŀ����ֵ����Ȼ������������������ĸ���ķ�Χ");
				}
			}
			
			if (valueNum <= 1) {
				System.err.println("��������ȷ�����������1. ʹ�� -n ��������������Ŀ�ĸ���     2. ʹ�� -r ����������Ŀ����ֵ����Ȼ������������������ĸ���ķ�Χ");
			}
		}
		int i = 0;
		int j = 0;
		IO io = new IO();
		while (j < exNum) {
			Func func = new Func();
			Exercise exercise = func.createFormula(valueNum - 1);
			Score answer = func.calculateExercise(exercise.aQuestion);
			String endAnswer = null;
			if (answer.canBeUse) {
				if (answer.down == 1) {
					endAnswer = answer.up + "";
					io.writeExercise(++j + ".  " + exercise.aQuestion + " = " + "\r\n", 0);
					io.writeExercise(j + ".  " + endAnswer + "\r\n", 1);
				} else if (answer.up > answer.down) {
					endAnswer = answer.up / (answer.down - answer.up % answer.down) + "'" + answer.up % answer.down + "/"  + answer.down;
					io.writeExercise(++j + ".  " + exercise.aQuestion + " = " + "\r\n", 0);
					io.writeExercise(j + ".  " + endAnswer + "\r\n", 1);
				}							
			} else {
				continue;
			}
		}
		
	}

}
