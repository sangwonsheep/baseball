package baseball;

import java.util.Scanner;

public class Baseball {

	public static int[] rand() {
		int[] answer = new int[3];
		while(true) {
			answer[0] = (int)(Math.random() * 9) + 1;
			answer[1] = (int)(Math.random() * 9) + 1;
			answer[2] = (int)(Math.random() * 9) + 1;
			
			if(!(answer[0] == answer[1] || answer[0] == answer[2] || answer[1] == answer[2]))
				break;
		}
		return answer;
	}

	public static int[] createQuestion(int n) {
		int[] question = new int[3];

		for(int i = question.length-1; i >= 0; i--) {
			question[i] = n % 10;
			n = n / 10;
		}

		return question;
	}

	/**
	 * strikeBall[0] = strike
	 * strikeBall[1] = ball
	 */
	public static int[] checkResult(int[] result, int n) {
		int[] strikeBall = new int[2]; 
		int[] question = createQuestion(n);

		for(int i = 0; i < question.length; i++) {
			strikeBall = checkBall(question, result, strikeBall, i);
		}	
		
		return strikeBall;
	}

	public static int[] checkBall(int[] question, int[] result, int[] strikeBall, int i) {
		for(int j = 0; j < result.length; j++) {
			if(question[i] == result[j] && i == j) {
				strikeBall[0]++;
			}
			else if(question[i] == result[j] && i != j) {
				strikeBall[1]++;
			}
		}

		return strikeBall;
	}
	
	public static void printBall(int[] strikeBall) {
		if(strikeBall[0] > 0)
			System.out.print(strikeBall[0] + " 스트라이크 ");
		if(strikeBall[1] > 0)
			System.out.print(strikeBall[1] + "볼");
		if(strikeBall[0] == 0 && strikeBall[1] == 0)
			System.out.print("낫싱");
		System.out.println();
	}

	public static void print(Scanner input) {
		int[] answer = rand();
		while(true) {
			System.out.print("숫자를 입력해주세요 ex)123 : ");
			int n = input.nextInt();
			int[] strikeBall = checkResult(answer, n);
			printBall(strikeBall);
			if(strikeBall[0] == 3) {
				System.out.println(strikeBall[0] + "개의 숫자를 모두 맞히셨습니다! 게임 종료");
				break;
			}
		}
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		print(input);
		input.close();
	}

}
