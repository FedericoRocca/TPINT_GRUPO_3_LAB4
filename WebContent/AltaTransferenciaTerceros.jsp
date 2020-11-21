<%@page import="dominio.Account"%>
<%@page import="dominio.User"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%
    User userLogin = new User(); 
    userLogin = (User)session.getAttribute("userLogin");
%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Banco UTN - Transferencia</title>

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">


   <script>
      function RemoveReadOnly(idInput) {
         $("#"+idInput).removeAttr("readonly");       
      };
   </script>
</head>
<body id="page-top">
	
	<div id="wrapper">

		<!-- Sidebar -->
		<!-- INCLUYO EL MENÚ ACÁ -->
		<jsp:include page="MenuCliente.html"></jsp:include> 
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
					<!-- Sidebar Toggle (Topbar) -->
					<button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>

					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">
						<div class="topbar-divider d-none d-sm-block"></div>
						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow">
							<a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 
								<span class="mr-2 d-none d-lg-inline text-gray-600 small">${userLogin.getFirstName()} ${userLogin.getLastName()}</span> 
								<img class="img-profile rounded-circle" src="img/profile.png">
							</a> 
							<!-- Dropdown - User Information -->
							<div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
								<a class="collapse-item" href="ServletLogin?btnLogout=1"><i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i><span>Cerrar sesi�n</span> </a>
							</div>
						</li>
					</ul>
				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">
					<!-- Page Heading -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">Nueva transferencia</h1>
					</div>
					
					<form method="post" action="ServletTransferencias">
					<%
						ArrayList<Account> listAccount = new ArrayList<Account>();
						if(request.getAttribute("listAccount") != null)
						{
							listAccount = (ArrayList<Account>) request.getAttribute("listAccount");
						}
					%>
						<div class="form-row">
							<div class="form-group col-md-3">
								<label>Origen</label>
								<select class="custom-select" name="textOrigen" id="accountOrigen">
								<option selected>Seleccione...</option>
   								<%
   									for(Account aO : listAccount)	{
								%>									
							      	<option value="<%=aO.getAccountNumber() %>"><%=aO.getAccountNumber()%></option>
							   	<%
									} 
								%>
								</select>  
								
     				
							</div>	
							
							<div class="form-group col-md-3">								
							<label for="title">Monto actual</label>
							<input class="form-control" required="required" id="txtMontoInicial" name="txtMontoInicial" type="number" disabled>        											     				
							</div>	
											
						</div>
						<div class="form-row">
							<div class="form-group col-md-3">
								<label for="title">Cantidad</label>
								<input class="form-control" required="required" name="txtCantidad" id="textAmountToTransferTer" type="text" placeholder="Ingrese cantidad">        				
							</div>		
						</div>
						<div class="form-row">
							<div class="form-group col-md-5">
								<label>Destinatario</label>
								<input class="form-control" required="required"  name="textDestinatario" id="accountDestino"  type="text" placeholder="CBU">  
							</div>	
							
							<div class="form-group col-md-3">
								<label for="title">Monto actual</label>
								<input class="form-control" required="required" id="txtMontoFinal" name="txtMontoFinal" type="number" disabled>        				
							</div>	
						</div>

						<button class="btn btn-primary mt-2" type="button" data-toggle="modal" value="Enviar" data-target="#exampleModal" onclick="mostrarTransferencia()">Enviar</button>
					
						<%
							if (request.getAttribute("estadoTransferencia") != null) {
						%>
						<div class="alert alert-success col-md-3" role="alert">
							Transferencia realizada con exito!
						</div>

						<%
							}
						%>	
						
					</form>
					
						<!-- Modal -->
								<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
								  <div class="modal-dialog" role="document">
								    <div class="modal-content">
								      <div class="modal-header">
								        <h5 class="modal-title" id="exampleModalLabel">Datos de transferencia</h5>
								        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
								          <span aria-hidden="true">&times;</span>
								        </button>
								      </div>
								      <div class="modal-body">
								      <form method="post" action="ServletTransferencias">
								      
								      
								      <div class="card"> 
								      <div class="card-body">
								      	<h5 class="card-title">Cuenta de Origen</h5>								        
								        	Numero de cuenta
								        	<input class="form-control" type="text" name="txtCtaOrigen" id="inputCtaOrigen" readonly> 
								        	Saldo
								        	<input class="form-control" type="text"  name="txtOrigenModal" id="inputMontoOrigen" readonly> 								      
								      </div>
					            
						            <div class="card-body">
						             <h5 class="card-title">Cuenta de Destino</h5>
							            Numero de CBU
							            <div class="input-group">		
								        <input class="form-control" name="txtCtaDestino" id="inputCtaDestino" type="text" readonly>
								        </div>
								        Saldo
								        <div class="input-group">			        						 
								        <input class="form-control" name="txtDestinoModal" id="inputMontoDestino" type="text" readonly> 
								        </div>
							        </div>								 								      
								      <div class="modal-footer">
								        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
								        <button type="submit" name="btnPedirTransferencia" class="btn btn-primary">Transferir</button>
								       </div>
								      </div>
								      </form>
								    </div>
								   
								  </div>
								  
								</div>
							</div>
				
				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; Banco UTN 2020</span>
					</div>
				</div>
			</footer>
			<!-- End of Footer -->
		</div>
		<!-- End of Content Wrapper -->
	</div>

	<!-- End of Page Wrapper -->
<script>
	
	 var selectO = document.getElementById('accountOrigen');
	 var selectD = document.getElementById('accountDestino');
		 		
	 var selectedOption = null;	 
	 var selectedOptionTextO = null;
	 var selectedOptionTextD = null;
	 
	 selectO.addEventListener('change',
   		function(){
   	  	selectedOption = this.options[selectO.selectedIndex];
   	 	selectedOptionTextO = this.options[selectO.selectedIndex].text;
   	  	$("#txtMontoInicial").val(selectedOption.value);  	  	
   	 	
   	  	var cuentaOrigen = $("txtMontoInicial").val(selectedOption.text);  	 	
   	  	console.log("1era Cuenta: " + selectedOptionTextO);
   	 	$("#divDestino").show();
     });

     selectD.addEventListener('change',
   		function(){
    	 if(selectD.selectedIndex == selectO.selectedIndex ){
    		 alert(" En serio vas a transferir a tu misma cuenta?");
    		 $("#divDestino").hide();
    	 }
    	 else{
    		selectedOption = this.options[selectD.selectedIndex];
    		selectedOptionTextD = this.options[selectD.selectedIndex].text;
    		console.log("2da Cuenta: " + selectedOptionTextD);
    	 	$("#txtMontoFinal").val(selectedOption.value);
    	 }   	 		
     });
	
     	function mostrarTransferencia(){
		$('#exampleModal').modal('show');			
		var montoTransferencia = $('#textAmountToTransferTer').val();
		
 		var montoOrigen = $('#txtMontoInicial').val();
 		var saldoOrigen = parseFloat(montoOrigen) - parseFloat(montoTransferencia);
 		var montoDestino = $('#txtMontoFinal').val();
 		var saldoDestino = parseFloat(montoDestino) + parseFloat(montoTransferencia);
 		
//  		console.log("montoOrigen: " + montoOrigen);
//  		console.log("saldo Actual 1era cuenta: " + saldo);
//  		console.log("Monto a transferir:" + montoTransferencia);	
//  		console.log("montoDestino:" + montoActual);s
		$('#inputCtaOrigen').val(selectedOptionTextO);
 		$('#inputMontoOrigen').val(saldoOrigen); //SALDO ACTUAL DE LA PRIMERA CUENTA
 		
 		$('#inputCtaDestino').val(selectedOptionTextD);
 		$('#inputMontoDestino').val(saldoDestino); // MONTO ACUMULADO DE LA SEGUNDA CUENTA

 	};   
	</script>
	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js"></script>
	
</body>
</html>