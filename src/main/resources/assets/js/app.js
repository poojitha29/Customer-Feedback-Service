$(":button").click(function() {
	var id = this.id.split("-");
	var ownerId = id[0];
	var buttontype = id[1];
	var productId = id[2];
	//alert(ownerId+buttontype+productId);
	if (buttontype=="Reviewable") { 
		//alert('about to set reviewable');
		$.ajax({
			  url: '/cust-feedback/v1/owners/'+ownerId+'/products/'+productId+'?canReview=true',
			  type: 'PUT',
			  contentType:"application/json",
			  success: function(data) {
			    //alert('Load was performed.');
				  location.reload();
			  }
			});
	}
	
	if (buttontype=="SetTemplate") {
		if(this.firstChild.nodeValue == "Set 5-Star Rating")
		$.ajax({
			  url: '/cust-feedback/v1/owners/'+ownerId+'/products/'+productId+'/canReview/true?setTemplate=false',
			  type: 'PUT',
			  contentType:"application/json",
			  success: function(data) {
			    //alert('5 Star rating template selected');
				  location.reload();
			  }
			});
		
		if(this.firstChild.nodeValue == "Set Like/Unlike")
			$.ajax({
				  url: '/cust-feedback/v1/owners/'+ownerId+'/products/'+productId+'/canReview/true?setTemplate=true',
				  type: 'PUT',
				  contentType:"application/json",
				  success: function(data) {
				    //alert('Like/Unlike template selected');
					  location.reload();
				  }
				});
	}
	
	if (buttontype=="Helpfulness") {
		//alert("Helpfulness?");
		if(this.firstChild.nodeValue == "Yes")
			$.ajax({
				  url: '../../'+ownerId+'/canReview/true/reviews/'+productId+'?isHelpful=true',
				  type: 'PUT',
				  contentType:"application/json",
				  success: function(data) {
				    //alert('Like/Unlike template selected');
					  location.reload();
				  }
				});

		
		if(this.firstChild.nodeValue == "No")
			$.ajax({
				  url: '../../'+ownerId+'/canReview/true/reviews/'+productId+'?isHelpful=false',
				  type: 'PUT',
				  contentType:"application/json",
				  success: function(data) {
				    //alert('Like/Unlike template selected');
					  location.reload();
				  }
				});
	}
	
	
	
});


$("#login").submit(function(event){
	//alert("Form Submitted");
	//window.location.reload();
	event.preventDefault();
	var $form = $( this );
	var userName = $form.find( "input[name='userName']" ).val();
	var formdata = $form.serialize();
	$.ajax({
		  type: 'POST',
		  data:formdata,
		  url: '/cust-feedback/authenticate/',
		  success: function() {
			    alert('Login');
			    window.location = '/cust-feedback/authenticate/'+userName;
			  },
			error:function() {
				alert("Invalid Username or Password");
			}
	});
});

