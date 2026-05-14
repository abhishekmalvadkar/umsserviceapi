# URL Management System

## Actors

* Customer
* Admin
* System

## Create Short URL

* Create short URL form should have below fields:
  * Title (Mandatory)
  * Original Url (Mandatory)
  * slug (Optional)
* If user does not give slug then system will generate slug else not

### Constraints

* If user does not give mandatory fields then show message just below that field:
  * "This field is required"
* If user give invalid original url then show message just below that field:
  * "Please enter a valid URL (e.g. https://example.com)"
* User should not be able to enter more than 2048 characters for original link and original link input box should show
  how many character used and how many remaining (e.g 2/2048)
* User should not be able to enter more than 128 characters for title and title input box should show
  how many character used and how many remaining (e.g 4/128)

## My Short URLs

* This will be short url list page
* This will contain below columns in table format:
  * Short Url
    * e.g "https://ourdomain.com/slug..."
    * Will show only first 10 characters and full short url will be shown on tooltip on hover
  * Original Url
    * It should show two things in two lines of single column
      * Title
      * Original Url
    * Both should show only first 10 characters and full original url or title will be shown on tooltip on hover
    * e.g:
      * "My Short URL Title...
        https://original-url.com...
        "
  * Status
    * e.g (Active , Inactive)
    * Active should show with white text and green background
    * Inactive should show with white text and light gray background
  * Created On
    * e.g "5 Jan 2026 11:33 AM"
  * Actions
    * crossed eye icon with tooltip "Deactivate url"
      * Once user click on it then show toaster with message:
        * "Url deactivated successfully!"
      * if url is deactivated then it should eye icon with tooltip "Activate url"
        * Once user click on it then show toaster with message:
        * "Url activated successfully!"
    * copy icon with tooltip "Copy Url"
      * When user click on that copy icon then toaster should show message:
        * "Url copied successfully!"
