GET: $(document).ready(
		function() {

			// GET REQUEST
			$("#accessportal1").click(function(event) {
				event.preventDefault();
				ajaxGet1();
			});

			$("#accessportal2").click(function(event) {
            				event.preventDefault();
            				ajaxGet2();
            			});

			// DO GET
			function ajaxGet1() {
				$.ajax({
					url : "http://localhost:8080/access1",
					success : function(result) {
						$("#apiResponse1").html(result);
					}
				});
			}

			function ajaxGet2() {
            				$.ajax({
            					url : "http://localhost:8080/access2",
            					success : function(result) {
            						$("#apiResponse2").html(result);
            					}
            				});
            			}
		})