$(document).ready(function()
{
	$("#alertSuccess").hide();
	$("#alertError").hide();
}); 

//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateHospitalForm();
	if (status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	// If valid------------------------
	var type = ($("#hidHospitalIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		 url : "HospitalsAPI",
		 type : type,
		 data : $("#formHospital").serialize(),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onHospitalSaveComplete(response.responseText, status);
		 }
	});

});

function onHospitalSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divHospitalsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error")
	{
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else
	{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidHospitalIDSave").val("");
	$("#formHospital")[0].reset();
}


//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	 $("#hidHospitalIDSave").val($(this).closest("tr").find('#hidHospitalIDUpdate').val());
	 $("# Hospital_Name").val($(this).closest("tr").find('td:eq(0)').text());
	 $("#Hospital_Address").val($(this).closest("tr").find('td:eq(1)').text());
	 $("#Hosiptal_PhoneNo").val($(this).closest("tr").find('td:eq(2)').text());
	 $("#Hospital_City").val($(this).closest("tr").find('td:eq(3)').text());
	 $("#Hospital_Email").val($(this).closest("tr").find('td:eq(4)').text());
	
}); 


$(document).on("click", ".btnRemove", function(event)
{
	 $.ajax(
	 {
		 url : "HospitalsAPI",
		 type : "DELETE",
		 data : "hid=" + $(this).data("Hospital_ID"),
		 dataType : "text",
		 complete : function(response, status)
		 {
			 onPatientDeleteComplete(response.responseText, status);
		 }
	 });
});
		 
function validateHospitalForm()
{
	 // NAME
	 if ($("# Hospital_Name").val().trim() == "")
	 {
		 return "Insert Hospital Name.";
	 }
	// ADDRESS
	 if ($("#Hospital_Address").val().trim() == "")
	 {
		 return "Insert Hospital Address.";
	 }
	// PHONENO
	 if ($("# Hosiptal_PhoneNo").val().trim() == "")
	 {
		 return "Insert Hospital Phoneno.";
	 }
	// CITY
	 if ($("# Hosiptal_City").val().trim() == "")
	 {
		 return "Insert Hospital City.";
	 }
	 
	// EMAIL
	 if ($("#Hospital_Email").val().trim() == "")
	 {
		 return "Insert Hospital Email.";
	 }
	 
	
	 return true;
}

