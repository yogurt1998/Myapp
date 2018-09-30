import java.awt.List;
import java.util.ArrayList;
import java.util.Stack;

import javax.print.attribute.standard.RequestingUserName;
import javax.swing.SwingUtilities;

public class Func {
	
	private static final int String = 0;
	
	// 生成四则运算式
	public Exercise createFormula(int n) {
		
		String question1 = null;
		
		String num1, num2, num3, num4; // 四个运算数
		
		String char1, char2, char3; // 三个运算符
		
		char1 = randomChar();
		char2 = randomChar();
		char3 = randomChar();
		
		num1 = (int)(Math.random()*2) == 0 ? Integer.toString((int)Math.round(Math.random()*(n))) : createScore(n);
		num2 = (int)(Math.random()*2) == 0 ? Integer.toString((int)Math.round(Math.random()*(n))) : createScore(n);
		num3 = (int)(Math.random()*2) == 0 ? Integer.toString((int)Math.round(Math.random()*(n))) : createScore(n);
		num4 = (int)(Math.random()*2) == 0 ? Integer.toString((int)Math.round(Math.random()*(n))) : createScore(n);
		
		String question2 = num1 + ' ' + char1 + ' ' + num2;
		
		String question3[] = {question2 + ' ' + char2 + ' ' + num3,
				'(' + question2 + ')' + ' ' + char2 + ' ' + num3,
				num1 + ' ' + char1 + ' ' + '(' + num2 + ' ' + char2 + ' ' + num3 + ')'};
		
		String question4[] = {question3[(int)(Math.random()*3)] + ' ' + char3 + ' ' + num4,
				'(' + question3[(int)(Math.random()*3)] + ')' + ' ' + char3 + ' ' + num4,
				num4 + ' ' + char3 + ' ' + '(' + question3[(int)(Math.random()*3)] + ')',
				'(' + question2 + ')' + ' ' + char2 + ' ' + num3 + ' ' + char3 + ' ' + num4,
				question2 + ' ' + char2 + ' ' + '(' + num3 + ' ' + char3 + ' ' + num4 + ')',
				num1 + ' ' + char1 + ' ' + '(' + num2 + ' ' + char2 + ' ' + num3 + ')' + ' ' + char3 + ' ' + num4};
		
		switch ((int)(Math.random()*3)) {
		case 0:
			question1 = question2;
			break;
		case 1:
			question1 = question3[(int)(Math.random()*3)];
			break;
		case 2:
			question1 = question4[(int)(Math.random()*6)];
			break;
		default:
			break;
		}
		
		Exercise exercise = new Exercise(num1, num2, num3, num4, char1, char2, char3, question1);
		
		return exercise;
	}
	
	// 分数生成器
	public String createScore(int n) {
		int upNum, downNum;
		do {
			upNum = (int)Math.round(Math.random()*(n));
			downNum = (int)Math.round(Math.random()*(n-1)+1);
		} while (upNum / downNum > n);
		
		return upNum > downNum ?  upNum / (downNum - upNum % downNum) + "'" + (upNum % downNum) + "/" + downNum
				: upNum + "/" + downNum;
	}
	
	// 随机运算符
	private String randomChar() {
		int key = (int)(Math.random()*4);
		switch (key) {
		case 0:
			return "+";
		case 1:
			return "-";
		case 2:
			return "×";
		case 3:
			return "÷";
		default:
			break;
		}
		return "";
	}
	
	// 最大公因数
	private static int gcd(int numberA, int numberB) {
		if (numberB != 0) {
			if (numberA % numberB == 0) {
	            return numberB;
	        } else {
	            return gcd(numberB, numberA % numberB);
	        }
		} else {
			return -1;
		}
        
    }
	
	// 最小公倍数
	private static int lcm(int numberA, int numberB) {
		if (gcd(numberA, numberB) != -1) {
			return numberA * numberB / gcd(numberA, numberB);
		} else {
			return -1;
		}
    }
	
	// 判断为运算符
	private boolean isOperator(char c) {
		if (c == '+' || c == '-' || c == '×' || c== '÷') {
			return true;
		}
		return false;
	}
	
	private boolean sIsOperator(String s) {
		if (s.equals("+") || s.equals("-") || s.equals("×") || s.equals("÷")) {
			return true;
		}
		return false;
	}
	
	// 打印list
	private void printList(ArrayList<String> list) {
		for (int i = 0; i < list.size(); i++ ) {
			System.out.println("第一列为                         " + list.get(i));
		}
	}
	
	
	// 计算运算式
	public Score calculateExercise(String s) {
		ArrayList<String> list = changeToPostfixEx(stringsToList(s));
		Score answer = calculateHEx(list);
		return answer;	
	}
	
	// String转为list
	private ArrayList<String> stringsToList(String s) {
		ArrayList<String> list = new ArrayList<>();
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			if((c >= '0' && c <= '9') || c == '\'' || c == '/') {
				temp.append(c);
			} else {
				String string = temp.toString();
				if (!string.isEmpty()) {
					list.add(string);
				}
				temp = new StringBuilder();
			}
			if (i == s.length()-1) {
				String string = temp.toString();
				if (!string.isEmpty()) {
					list.add(string);
				}
			}
			if (isOperator(c))
				list.add(c + "");
			if (c == '(' || c == ')')
				list.add(c + "");
		}
		return list;
	}
	
	// 中缀表达式转为后缀表达式
	private ArrayList<String> changeToPostfixEx(ArrayList<String> list) {
		Stack<String> stack = new Stack<String>();
		ArrayList<String> pList = new ArrayList<String>();
		int level = -1;
		for (int i = 0; i < list.size(); ++i) {
			String aList = list.get(i);
			if (!sIsOperator(aList) && !aList.equals("(") && !aList.equals( ")")) {
				pList.add(aList);
			} else if (aList.equals("(")) {
				stack.push(aList);
				level = 0;
			} else if (aList.equals( ")")){
				while (!stack.peek().equals("(")) {
					pList.add(stack.pop());
				}
				stack.pop();
				if (stack.empty())
					level = -1;
				else {
					level = getLevel(stack.peek());
				}
			} else if (getLevel(aList) > level) {
				stack.push(aList);
				if (level != 0)
					level = getLevel(stack.peek());
			} else {
				while (!stack.empty() && getLevel(aList) <= level) {
					pList.add(stack.pop());
				}
				stack.push(aList);
				level = getLevel(stack.peek());
			}
		}
		while (!stack.empty()) {
			pList.add(stack.pop());
		}
		return pList;
 	}
    
    //判断是否操作符
    private static boolean isOperator(String oper){
        if (oper.equals("+")||oper.equals("-")||oper.equals("/")||oper.equals("*")
                ||oper.equals("(")||oper.equals(")")) {
            return true;
        }
        return false;
    }
    //计算操作符的优先级
    private static int priority(String s){
        switch (s) {
            case "+":return 1;
            case "-":return 1;
            case "*":return 2;
            case "/":return 2;
            case "(":return 3;
            case ")":return 3;
            default :return 0;
        }
    }
	
	// 计算后缀表达式
	private Score calculateHEx(ArrayList<String> list) {
		
		boolean beUse = true; // 是否使用
		
		Stack<String> stack = new Stack<>();
			
			for (int i = 0; i < list.size(); ++i) {
				if (!sIsOperator(list.get(i))) {
					stack.push(list.get(i));
				} else {
					Score result = new Score();
					String resultS = null;
					switch (list.get(i)) {
					case "+":
						String a1 = stack.pop();
						String a2 = stack.pop();
						result = addScore(a2, a1);
						resultS = result.up + "/" + result.down;
						stack.push(resultS);
						break;
					case "-":
						String a3 = stack.pop();
						String a4 = stack.pop();
						result = subtractScore(a4, a3);
						resultS = result.up + "/" + result.down;
						stack.push(resultS);
						break;
					case "×":
						String a5 = stack.pop();
						String a6 = stack.pop();
						result = mulScore(a6, a5); 
						resultS = result.up + "/" + result.down;
						stack.push(resultS);
						break;
					case "÷":
						String a7 = stack.pop();
						String a8 = stack.pop();
						result = divScore(a8, a7); 
						resultS = result.up + "/" + result.down;
						stack.push(resultS);
						break;
					default:
						break;
					}
					beUse = result.canBeUse;
				}
				
				if (!beUse) 
					break;
			}
		Score result = new Score();
		result.canBeUse = beUse;
		if (result.canBeUse) {
			String endResult = stack.pop();
			result.changeScore(endResult);
			list.add(endResult);
			simplifyScore(result);
		}
		return result;
	}
	
	// 判断优先级
	private int getLevel(String s) {
		int level = 1;
		switch (s) {
		case "(":
			level = 0;
			break;
		case "+":
			level = 1;
			break;
		case "-":
			level = 1;
			break;
		case "×":
			level = 2;
			break;
		case "÷":
			level = 2;
			break;
		default:
			break;
		}
		return level;
	}
	
	// 分数加法
	private Score addScore(String numbleA, String numbleB) {
		Score result = new Score();
		Score sA = new Score();
		Score sB = new Score();
		sA.changeScore(numbleA);
		sB.changeScore(numbleB);
		result.down = lcm(sA.down, sB.down);
		result.up = sA.up * result.down / sA.down + sB.up * result.down / sB.down;
		if (result.down == -1)
			result.canBeUse = false;
		return result;
	}
	
	// 分数减法
	private Score subtractScore(String numbleA, String numbleB) {
		Score result = new Score();
		Score sA = new Score();
		Score sB = new Score();
		sA.changeScore(numbleA);
		sB.changeScore(numbleB);
		result.down = lcm(sA.down, sB.down);
		result.up = sA.up * result.down / sA.down - sB.up * result.down / sB.down;
		if (result.up < 0)
			result.canBeUse = false;
		if (result.down == -1)
			result.canBeUse = false;
		return result;
	}
	
	// 分数乘法
	private Score mulScore(String numbleA, String numbleB) {
		Score result = new Score();
		Score sA = new Score();
		Score sB = new Score();
		sA.changeScore(numbleA);
		sB.changeScore(numbleB);
		result.up = sA.up * sB.up;
		result.down = sA.down * sB.down;
		return result;
	}
	
	// 分数除法
	private Score divScore(String numbleA, String numbleB) {
		Score result = new Score();
		Score sA = new Score();
		Score sB = new Score();
		sA.changeScore(numbleA);
		sB.changeScore(numbleB);
		if (sB.up == 0 || sB.down == 0)
			result.canBeUse = false;
		result.up = sA.up * sB.down;
		result.down = sA.down * sB.up;
		return result;
	}
	
	// 分数化简
	private void simplifyScore(Score result) {
		int minCF = 1;
		do {
			minCF = gcd(result.up, result.down);
			result.up /= minCF;
			result.down /= minCF;
		} while (minCF != 1);
	}
	
}