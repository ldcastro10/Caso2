package LRU;

import java.util.ArrayList;

import Memoria.MemoriaReal;
import Memoria.TablaPagina;

public class Actualizacion extends Thread{

	/**
	 * Referencias a realizar en la ejecuci�n del proceso
	 */
	private ArrayList<Integer> referencias = new ArrayList<Integer>();

	/**
	 * Marcos de p�gina del proceso
	 */
	private MemoriaReal memoriaReal;

	/**
	 * Tablas de p�gina del proceso
	 */
	private TablaPagina tablaPagina;

	/**
	 * Referencia a las p�ginas referenciadas en el �ltimo intervalo
	 */
	private Integer[] bitsPaginas;

	/**
	 * N�mero total de fallos de p�gina ocurridos
	 */
	private int fallosPagina;

	private boolean terminado;

	public Actualizacion( MemoriaReal pMarcoPagina, TablaPagina pTablaPagina, ArrayList<Integer> referencias, Integer[] pBitsPaginas ) {
		this.memoriaReal = pMarcoPagina;
		this.tablaPagina = pTablaPagina;
		this.referencias = referencias;
		this.bitsPaginas = pBitsPaginas;		
	}

	private void leerReferencia(int posTablaPagina, int i) {
		synchronized (memoriaReal) {
			int posMarcoPagina = tablaPagina.obtenerRefMarcoPagina(posTablaPagina);
			if( posMarcoPagina != -1) {			
				bitsPaginas[posMarcoPagina] = 1;
			} else {
				fallosPagina++;
				posMarcoPagina = memoriaReal.falloPagina();
				tablaPagina.relacionar(posTablaPagina, posMarcoPagina);
				bitsPaginas[posMarcoPagina] = 1;
			}
		}		
	}

	public void run() {
		int posTablaPagina;
		for (int i = 0; i < referencias.size(); i++) {				
			try {
				posTablaPagina = referencias.get(i);
				leerReferencia(posTablaPagina, i);
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		memoriaReal.cambiarTerminar();
		System.out.println("N�mero de Fallo de P�ginas:" + fallosPagina);
	}
}
