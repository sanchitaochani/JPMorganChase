# JPMorganChase

PS - if possible, do run the app on API level 33 as I used the latest implementation of Bundle for passing data to the detail screen. I can add 
some code for compatibility on older versions but just wanted to flag this here.

Although I've worked with Dagger in the past, I ran into a bunch of issues getting it to work for this
project (metadata and binary issue), and adding Navigation library was also new to me. 
Plus I also spent a lot of time trying to write tests that didn't work (I was attempting to test the VM code but due to 
network calls happening on IO so livedata values weren't getting updated in tests.)


Given  more time I would like to - 
* have better UI for detail screen. I wanted to add some more fields but a lot of the fields were uncommon in all elements
and the requirements fields were not structured the same way (some were called requirements1_1, while another object 
would have requirements2_1). Overall I feel like the UI could be a lot better on the second screen and could display
more meaningful information, have more icons/images/fonts.
* swipe to refresh functionality 
* search functionality (by name/location)
* have a better looking action bar (also include search and back buttons)
* have standard theming, font, text styles, dimensions, colors for the app specified. 
* CTA on the error screen to reload 
* handle multiple errors using different UI screens instead of a generic screen 
(no network -> check internet, server error -> try again, timeout -> try again etc)
* handle some more null cases
* add error handling and loading to DetailScreen
* add UI tests (unfamiliar)
* add unit tests (ran into some issues here because of coroutines), given more time I would be able to add dispatchers as a
dependency injection and create a custom CoroutineRule for UTs to be able to test the code on different dispatchers
* create a generic wrapper class for Success, Loading, Error that I would be able to reuse for multiple use-cases and screens
* instead of using static pngs, have Android friendly vector icons that support multiple densities.
* I also think my detail_fragment.xml is very inefficient right now since it is using nested layouts. Would prefer to
re-write it to reuse view components and have a flatter hierarchy.
