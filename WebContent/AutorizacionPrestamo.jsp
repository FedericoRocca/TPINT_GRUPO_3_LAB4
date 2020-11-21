<%@page import="dominio.Loan"%>
<%@page import="dominio.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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

<title>Banco UTN - Autorizacion de prestamo</title>

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">

<!-- Styles for the datatables -->
<link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

</head>
<body id="page-top">
	<div id="wrapper">

		<!-- Sidebar -->
		<!-- INCLUYO EL MEN� AC� -->
		<jsp:include page="MenuAdmin.html"></jsp:include> 
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
				<!-- Topbar -->
				<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
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
								<img class="img-profile rounded-circle"src="img/profile.png">
							</a> <!-- Dropdown - User Information -->
							<div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
								<a class="collapse-item" href="ServletLogin?btnLogout=1"><i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i><span>Cerrar sesion</span> </a>
							</div>
						</li>
					</ul>
				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				
				<div class="container-fluid">
					<div class="card shadow mb-4">
						<div class="card-header py-3 form-row">
							<h6 class="m-0 font-weight-bold text-primary col-md-4">Autorizacion de prestamos</h6>
							<%
								if (request.getAttribute("estadoPrestamo") != null) {
							%>
							<div class="alert alert-success col-md-4" role="alert">
								Prestamo modificado!
							</div>
	
							<%
								}
							%>	
						</div>
						<%
							List<Loan> listaLn = new ArrayList<Loan>();
							if (request.getAttribute("listLoans") != null) {
								listaLn = (List<Loan>) request.getAttribute("listLoans");
							}
						%>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" cellspacing="0">
									<thead>
										<tr>
											<th>ID</th>
											<th>Cliente</th>
											<th>Cuenta</th>
											<th>Fecha</th>
                      						<th>Importe</th>
                      						<th>Prestamo</th>
                      						<th>Cant. Cuotas</th>
                      						<th> </th>
										</tr>
									</thead>
									<tbody>
										<%
										if(listaLn != null)
										for(Loan l : listaLn){
										%>
										<tr>
											<form action="ServletPrestamos" method="post">
											<td>
												<%=l.getId() %> 
												<input type="hidden" name="idLoan" value="<%=l.getId() %>"> 
											</td>
											<td><%=l.getDni() %></td>
											<td>
												<%=l.getAccountNumber() %>
												<input type="hidden" name="accountNumber" value="<%=l.getAccountNumber() %>"> 
											</td>
											<td><%=l.getLoanDate() %></td>
											<td><%=l.getAmountInt() %></td>
											<td>
												<%=l.getAmountReqByCustomer() %>
												<input type="hidden" name="amountReqByCustomer" value="<%=l.getAmountReqByCustomer() %>">
											</td>
											<td>
												<%=l.getAmountOfFees() %>
												<input type="hidden" name="amountOfFees" value="<%=l.getAmountOfFees() %>">
											</td>
											<td>
											<button type="submit" class="btn btn-success" name="btnAceptado">
											    <i class="fas fa-check"></i>
											</button>
											<button type="submit" class="btn btn-danger" name="btnRechazado">
											    <i class="fas fa-trash"></i>
											</button>
											</td>
											</form>
										</tr>
			                    		<%
										}
										%>
									</tbody>
								</table>
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

	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js"></script>
	
	<!-- Page level plugins -->
	<script src="vendor/datatables/jquery.dataTables.min.js"></script>
	<script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

	<!-- Page level custom scripts -->
	<script src="js/demo/datatables-demo.js"></script>
</body>
</html>