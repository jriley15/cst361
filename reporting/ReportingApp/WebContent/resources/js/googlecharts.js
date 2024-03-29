// Trevor Moore
// CST 361
// 09/29/2019
// This assignment was completed in collaboration with Jordan Riley.
// We used source code from the following websites to complete this assignment:
// https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/map


/**
 * Include in views that render Google Charts.
 */

	async function getCurrencies() 
	{
		// use the fetch API to hit our CurrencyREST endpoint
		let response = await fetch('/ReportingApp/rest/currency/getcurrencies');
		let data = await response.json()
		return data;
	}
	
	$(function() {
		// define array of arrays which will hold our headers and currency data.
		let currencyData = [['Country', 'Exchange Rate ($)']];
		// declare empty arrays for country and exchange rate data.
		let countries = [];
		let exchangeRates = [];
		
		// get our currencies
		getCurrencies()
		// handle json response
		.then(json => {
			// fill the country array with the response country data
			countries = json.data.map(currency => { return currency.currencyCountry });
			// fill the exchange rate array with the response exchange rate data
			exchangeRates = json.data.map(currency => { return currency.currencyUSDExchangeRate });

			// loop through the country length and fill the currency data array with our country and exchange rate data
			for (var n = 0; n < countries.length; n++) {
				currencyData.push([countries[n], exchangeRates[n]]);
			}
		})
		// catch any errors
		.catch(error => {
			// print the error to the console and alert the user
			console.error(error);
			alert('An error occured.');
		});
		
		// call on the Google GeoChart API to fill our div with the currency data
		// retrieved this boiler plate code from: https://developers.google.com/chart/interactive/docs/gallery/geochart
		google.charts.load('current', {
			'packages':['geochart'],
		 	'mapsApiKey': 'AIzaSyAofTrkfRftX9aK7IoCk30wRE9-Y7jabsI'
		});
		google.charts.setOnLoadCallback(drawRegionsMap);
		
		function drawRegionsMap() {
			var data = google.visualization.arrayToDataTable(currencyData);
			var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));
			chart.draw(data, {});
		}
	});
	
	/// Retrieved the following Polyfill from: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/map
	// Production steps of ECMA-262, Edition 5, 15.4.4.19
	// Reference: http://es5.github.io/#x15.4.4.19
	if (!Array.prototype.map) {

	  Array.prototype.map = function(callback/*, thisArg*/) {

	    var T, A, k;

	    if (this == null) {
	      throw new TypeError('this is null or not defined');
	    }

	    // 1. Let O be the result of calling ToObject passing the |this| 
	    //    value as the argument.
	    var O = Object(this);

	    // 2. Let lenValue be the result of calling the Get internal 
	    //    method of O with the argument "length".
	    // 3. Let len be ToUint32(lenValue).
	    var len = O.length >>> 0;

	    // 4. If IsCallable(callback) is false, throw a TypeError exception.
	    // See: http://es5.github.com/#x9.11
	    if (typeof callback !== 'function') {
	      throw new TypeError(callback + ' is not a function');
	    }

	    // 5. If thisArg was supplied, let T be thisArg; else let T be undefined.
	    if (arguments.length > 1) {
	      T = arguments[1];
	    }

	    // 6. Let A be a new array created as if by the expression new Array(len) 
	    //    where Array is the standard built-in constructor with that name and 
	    //    len is the value of len.
	    A = new Array(len);

	    // 7. Let k be 0
	    k = 0;

	    // 8. Repeat, while k < len
	    while (k < len) {

	      var kValue, mappedValue;

	      // a. Let Pk be ToString(k).
	      //   This is implicit for LHS operands of the in operator
	      // b. Let kPresent be the result of calling the HasProperty internal 
	      //    method of O with argument Pk.
	      //   This step can be combined with c
	      // c. If kPresent is true, then
	      if (k in O) {

	        // i. Let kValue be the result of calling the Get internal 
	        //    method of O with argument Pk.
	        kValue = O[k];

	        // ii. Let mappedValue be the result of calling the Call internal 
	        //     method of callback with T as the this value and argument 
	        //     list containing kValue, k, and O.
	        mappedValue = callback.call(T, kValue, k, O);

	        // iii. Call the DefineOwnProperty internal method of A with arguments
	        // Pk, Property Descriptor
	        // { Value: mappedValue,
	        //   Writable: true,
	        //   Enumerable: true,
	        //   Configurable: true },
	        // and false.

	        // In browsers that support Object.defineProperty, use the following:
	        // Object.defineProperty(A, k, {
	        //   value: mappedValue,
	        //   writable: true,
	        //   enumerable: true,
	        //   configurable: true
	        // });

	        // For best browser support, use the following:
	        A[k] = mappedValue;
	      }
	      // d. Increase k by 1.
	      k++;
	    }

	    // 9. return A
	    return A;
	  };
	}
	
	
	