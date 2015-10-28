

/* @rm Author Gregorian Pavluschenkow
 * @rmDate 2054-02-23
 * @rmSummary Looping Function Calculating the sum of the digits
 * @rmDescription Loopande funktion 
 * @rmPriority Low
 * @rmStep [step] skapa en funktion
 * @rmStep [step] skapa en body
 */
function sumDigits(num) {
     var i, sum = 0;                  // can declare two variables at once

     for (i = 1; i <= num; i++) {
             sum += i;              // add each number to sum (ie, 1 + 2 + ...+ num)
     }

     // Display result
     alert("The sum of the digits from 1 to "+ num + " is:\n\n\t " + sum);
}



<BODY>

Looping Functions - Calculate the sum of the digits.

<FORM NAME="SumNums">
     The sum of the digits from 1 to:
     <INPUT TYPE="text" NAME="charNum">
     <INPUT TYPE="button" VALUE="Calculate"
       onClick="sumDigits(SumNums.charNum.value)">
</FORM>