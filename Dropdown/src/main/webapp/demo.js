$(document).ready(function() {
    $("#country").click(function() {
	        $.ajax({
            url: "http://localhost:8080/Dropdown/CountryStateDao",
            method: "GET",
            data: {
            operation: 'country'
            },
                success: function(data, textStatus, jqXHR) {
                console.log(data);
                let obj = $.parseJSON(data);

                var length = $('#country').children('option').length;
                if (length == 1) {
                    //$('#country').empty();
                    $.each(obj, function(key, value) {
                        $('#country').append('<option value="' + value.id + '">' + value.name + '</option>')
                    });
                }

                // $('select').formSelect();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                $('#country').append('<option>Country Unavailable</option>');
            },
            cache: false
        });

    });


    $('#country').change(function() {
        $('#state').find('option').remove();
        $('#state').append('<option>Select State</option>');
        $('#city').find('option').remove();
        $('#city').append('<option>Select City</option>');

        let cid = $('#country').val();
        let data = {
            operation: "state",
            id: cid
        };
        $("#state").click(function() {
            $.ajax({
                url: "http://localhost:8080/Dropdown/CountryStateDao",
                method: "GET",
                data: data,
                success: function(data, textStatus, jqXHR) {
                    console.log(data);
                    let obj = $.parseJSON(data);
                    var length = $('#state').children('option').length;
                    if (length == 1) {
                        $.each(obj, function(key, value) {
                            $('#state').append('<option value="' + value.id + '">' + value.name + '</option>')
                        });
                    }
                    //    $('select').formSelect();
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    $('#state').append('<option>State Unavailable</option>');
                },
                cache: false
            });
        });

    });
	
	
	$('#state').change(function () {
                    $('#city').find('option').remove();
                    $('#city').append('<option>Select City</option>');

                    let sid = $('#state').val();
                    let data = {
                        operation: "city",
                        id: sid
                    };

                    $.ajax({
                        url: "http://localhost:8080/Dropdown/CountryStateDao",
                        method: "GET",
                        data: data,
                        success: function (data, textStatus, jqXHR) {
                            console.log(data);
                            let obj = $.parseJSON(data);
                             var length = $('#city').children('option').length;
						if(length == 1) {
                            $.each(obj, function (key, value) {
                                $('#city').append('<option value="' + value.id + '">' + value.name + '</option>')
                            });
                            }
                         //   $('select').formSelect();
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            $('#city').append('<option>City Unavailable</option>');
                        },
                        cache: false
                    });
                });

});