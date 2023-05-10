package test.mypac;

public class Rect {
	//필드 
	public int width;
	public int height;
	//메소드
	public void printArea() {
		//면적 계산하기 
		int area=this.width*this.height;
		//콘솔창에 면적 출력하기 
		System.out.println("사각형의 넓이:"+area);
	}
}
