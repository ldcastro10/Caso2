package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import LRU.Actualizacion;
import LRU.Envejecimiento;
import Memoria.MemoriaReal;
import Memoria.TablaPagina;

public class Main {

	/**
	 * Arreglo que representa la referencia a las páginas en cada ciclo
	 */
	public static Integer[] bitsPaginas;
	
	/**
	 * Arreglo con todas las referencias que se van a realizar
	 */
	public static ArrayList<Integer> referenciasPaginas = new ArrayList<Integer>();
	
	/**
	 * Marcos de página en memoria RAM
	 */
	public static MemoriaReal memoriaReal;
	
	/**
	 * Páginas del proceso 
	 */
	public static TablaPagina tablaPaginas;
	
	
	
	public static void main(String[] args) {

		/**
		 * Referencias a realizar en la ejecución del proceso
		 */
		ArrayList<Integer> referencias = new ArrayList<Integer>();

		try {
			File archivo = new File("./data/referencias4.txt");
			FileReader fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);

			String linea = br.readLine();
			int marcosPagina = Integer.parseInt(linea);
			linea = br.readLine();
			int paginasProceso = Integer.parseInt(linea);
			linea = br.readLine();
			double localidad = Double.parseDouble(linea);

			while((linea=br.readLine())!=null)
			{
				referencias.add(Integer.parseInt(linea));
			}
			
			bitsPaginas = new Integer[marcosPagina];
			memoriaReal = new MemoriaReal(marcosPagina);
			tablaPaginas = new TablaPagina(paginasProceso);
						
			Actualizacion actualizacion = new Actualizacion( memoriaReal, tablaPaginas, referencias, bitsPaginas);
			Envejecimiento envejecimiento = new Envejecimiento( bitsPaginas, memoriaReal);
			
			actualizacion.start();
			envejecimiento.start();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
