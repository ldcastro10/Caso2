package Memoria;

public class MarcoPagina {
	
	private int contador;
	
	public MarcoPagina() {
		this.contador = 0;
	}
	
	public int darContador() {
		return contador;
	}
	
	public void  actualizar() {
		contador >>>= 1;		
	}
	
	public void sumar() {
		contador >>>= 1;
		contador = contador | 536870912;
	}
}
