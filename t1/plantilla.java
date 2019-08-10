import java.util.*;

/* tarea 1 de Algoritmos y estructuras de datos. Constaba de construir una imagen utilizando fractales. La solución se usó una matriz 
y recursivamente se obtuvo el resultado para los distintos n solicitados*/

public class PilaArena{

	public static void main(String[] args){

		Scanner sc = new Scanner(System.in);
		System.out.print("Ingrese numero de granos de arena: ");
		int n = sc.nextInt();
		long start = System.currentTimeMillis();
		int[][] mat = llenar(n);
		long end = System.currentTimeMillis();
		System.out.println("Tiempo de ejecucion: ");
		System.out.print(end-start);
		System.out.print(" Milisegundos ");
		Ventana ventana = new Ventana(700, "Pila de Arena");
		ventana.mostrarMatriz(mat);
		
	}

	private static int[][] llenar(int ng){
		int vecesdeC, restos, vecesdeC2, contadorA, x, i, j;
		contadorA=0;
		
		int[][] mat = new int[3][3];

		//expansion de la matriz
		int tamMat = (int) Math.ceil(Math.sqrt(ng)*(0.5));
		tamMat = tamMat*2;
		if (tamMat%2==0){
			tamMat+=3;
		}
		mat = new int[tamMat][tamMat];
		//centro de la matriz
		x = (mat.length-1)/2;

		//repartiendo arena, agregamos en el centro
		mat[x][x]+=ng;
		mat[x+1][x]+=1; mat[x-1][x]+=1; 
		mat[x][x-1]+=1; mat[x][x+1]+=1;
		mat[x][x]-=4;
		//ocurrio un derrumbe
		contadorA+=1;

		//iniciamos el ciclo que recorre la matriz y empieza a repartir
		int malas = 0; int buenas = 0;
		while(true){
		for(i=0 ; i < mat.length; i++){
			for(j=0 ; j < mat[i].length; j++){
					if (mat[i][j] >= 4){

						mat[i+1][j]+=1; mat[i-1][j]+=1; 
						mat[i][j-1]+=1; mat[i][j+1]+=1;
						mat[i][j]-=4;
						contadorA++;
						malas++;

					}
					else {
					buenas++;
					}
				}
			}
			//reviso cuando pasa por toda la matriz
			if (malas+buenas == mat.length*mat.length){
				if (malas == 0){
					break;
				}
				//si no reinicio mis contadores
				else{
					malas=0;
					buenas=0;
				}
			}

		}

		System.out.println( "centro de la matriz es la posicion: ("+x+","+x+"), "+"de la matriz tamano: " + mat.length);
		System.out.println( "veces que hubo derrumbes: " + contadorA);
		return mat;
	}

}
