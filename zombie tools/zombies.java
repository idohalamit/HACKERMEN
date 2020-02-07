package valuefinder;

import java.util.*;

public class zombies {
	public static short findBestBombingAdr(short[] results) {
		int mostHits = 0;
		int bestAdr = 0;
		for(int curAdr = 0; curAdr<results.length; curAdr++) {
			int hits = 0;
			for(int word = curAdr; word < curAdr+255; word += 4) 
				hits += results[word];
			if(hits >= mostHits) {
				mostHits = hits;
				bestAdr = curAdr;
			}
		}
		System.out.println("most hits: "+mostHits);
		return bestAdr;
	}
	public static void main(String[] args) {
		Random rando = new Random();
		short[] results = new short[65536];
		for (int c = 100000; c>0; c--) {
			int si = 0;

			int bx = rando.nextInt();
			bx &= 0xffff;
			//System.out.print(Integer.toHexString(bx));
			int cx = rando.nextInt();
			cx &= 0xffff;
			//System.out.print(", " + Integer.toHexString(cx));
			int dx = rando.nextInt();
			dx &= 0xffff;
			//System.out.print(", " + Integer.toHexString(dx));
			int di = rando.nextInt();
			di &= 0xffff;
			//System.out.print(", " + Integer.toHexString(di));

			int bp = di ^ bx ^ cx ^ dx;
			//System.out.print(", " + Integer.toHexString(bp));

			int ax = di; // 000000A8 89C7 mov di,ax

			ax ^= bx; // 000000AA 31D8 xor ax,bx
			ax ^= cx; // 000000AC 31C8 xor ax,cx
			ax ^= dx; // 000000AE 31D0 xor ax,dx

			ax = di; // 000000B4 89F8 mov ax,di
			ax = ax & 0x5555; // 000000B6 255555 and ax,0x5555
			si = bx; // 000000B9 89DE mov si,bx
			si = si & 0x5555; // 000000BB 81E65555 and si,0x5555
			ax += si; // 000000BF 01F0 add ax,si
			ax &= 0xffff;
			si = cx; // 000000C1 89CE mov si,cx
			si = si & 0x5555; // 000000C3 81E65555 and si,0x5555
			ax += si; // 000000C7 01F0 add ax,si
			ax &= 0xffff;
			si = dx; // 000000C9 89D6 mov si,dx
			si = si & 0x5555; // 000000CB 81E65555 and si,0x5555
			ax += si; // 000000CF 01F0 add ax,si
			ax &= 0xffff;
			si = bp; // 000000D1 89EE mov si,bp
			si = si & 0x5555; // 000000D3 81E65555 and si,0x5555
			ax += si; // 000000D7 01F0 add ax,si
			ax &= 0xffff;
			ax = ax >> 1; // 000000D9 D1E8 shr ax,1
			ax &= 0xffff;
			ax &= 0xaaaa; // 000000DB 25AAAA and ax,0xaaaa
			ax -= 0x2019; // 000000DE 2D1920 sub ax,0x2019
			ax &= 0xffff;
			
			if(results[ax] != Short.MAX_VALUE) results[ax]++;
			else break;
			//System.out.println(" : " + Integer.toString(ax)); // 000000E1 FFE0 jmp ax

		}
		
		System.out.println("WOW  !! "+Integer.toHexString((int) findBestBombingAdr(results)));
	}

}
