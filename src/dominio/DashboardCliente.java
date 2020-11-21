package dominio;

public class DashboardCliente 
{
	private float saldoTotal;
	private int cantidadTransferencias;
	private int autorizacionesAprobadas;
	
	
	public float getSaldoTotal() {
		return saldoTotal;
	}
	public void setSaldoTotal(float saldoTotal) {
		this.saldoTotal = saldoTotal;
	}
	public int getCantidadTransferencias() {
		return cantidadTransferencias;
	}
	public void setCantidadTransferencias(int cantidadTransferencias) {
		this.cantidadTransferencias = cantidadTransferencias;
	}
	public int getAutorizacionesAprobadas() {
		return autorizacionesAprobadas;
	}
	public void setAutorizacionesAprobadas(int autorizacionesAprobadas) {
		this.autorizacionesAprobadas = autorizacionesAprobadas;
	}
	
	
}
