package code_guru;

import java.util.*;

public class zombies {
	public static Scanner reader = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println("Please enter five numbers");
		
		int si = 0;
		
		int bx = reader.nextInt();
		int cx = reader.nextInt();
		int dx = reader.nextInt();
		int bp = reader.nextInt();
		int di = reader.nextInt();
		
		int ax = di; //000000A8  89C7              mov di,ax
		
		ax ^= bx; //000000AA  31D8              xor ax,bx
		ax ^= cx; //000000AC  31C8              xor ax,cx
		ax ^= dx; //000000AE  31D0              xor ax,dx
		
		if(ax != bp) {                    //000000B0  39E8              cmp ax,bp
			System.out.println("Fail.");  //000000B2  75E7              jnz 0x9b
		} else {
			ax = di;                      //000000B4  89F8              mov ax,di
			ax = ax & 21845;              //000000B6  255555            and ax,0x5555
			si = bx;                      //000000B9  89DE              mov si,bx
			si = si & 21845;              //000000BB  81E65555          and si,0x5555
			ax += si;                     //000000BF  01F0              add ax,si
			si = cx;                      //000000C1  89CE              mov si,cx
			si = si & 21845;              //000000C3  81E65555          and si,0x5555
			ax += si;                     //000000C7  01F0              add ax,si
			si = dx;                      //000000C9  89D6              mov si,dx
			si = si & 21845;              //000000CB  81E65555          and si,0x5555
			ax += si;                     //000000CF  01F0              add ax,si
			si = bp;                      //000000D1  89EE              mov si,bp
			si = si &21845;               //000000D3  81E65555          and si,0x5555
			ax += si;                     //000000D7  01F0              add ax,si
			ax = ax >> 1;                 //000000D9  D1E8              shr ax,1
			ax = ax & 43690;              //000000DB  25AAAA            and ax,0xaaaa
			ax = ax - 8217;               //000000DE  2D1920            sub ax,0x2019
			
			System.out.println("Jumped to adress: " + Integer.toHexString(ax)); // 000000E1  FFE0              jmp ax
			
		}
		
		

	}
	
	

}
