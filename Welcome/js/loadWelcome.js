/* 
 * Simple javascript file to get the current language from the browser
 * and display the proper welcome in that language if posssible.
 */
//document.write('<scr'+'ipt type="text/javascript" src="js/jquery.js" ></scr'+'ipt>');
var lang;

function getWelcome() {
    lang = getLanguage();
    if (!lang || !phrases[lang]) {
        lang = 'de';
    }
    document.getElementById('welcome').innerHTML = phrases[lang];
    
}

 function getLanguage() {

         if (navigator.language) {
             lang = navigator.language;
         } else if (navigator.userLanguage) {
             lang = navigator.userLanguage;
         }

         if (lang && lang.length > 2) {
             lang = lang.substring(0, 2);
         }

         return lang;
     }

var phrases = { /* translation table for page */
    en: ["<h1>Welcome!</h1><p>Lorem ipsum dolor sit amet, vide ignota id eos, esse sanctus vituperata eam ad. Eu indoctum voluptaria vis, mei ut cibo aliquando. In vix sapientem theophrastus intellegebat, et has liber fabulas posidonium, in per dico omnesque praesent. No quo assum deterruisset.</p>"],
    de: ["<h1>Willkommen!</h1><p>Lorem ipsum dolor sit amet, vide ignota id eos, esse sanctus vituperata eam ad. Eu indoctum voluptaria vis, mei ut cibo aliquando. In vix sapientem theophrastus intellegebat, et has liber fabulas posidonium, in per dico omnesque praesent. No quo assum deterruisset.</p>"]
};