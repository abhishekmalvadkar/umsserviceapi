## Lombok Learning

### How to add log in 1 step instead of 2 steps

* In Any component if you want to add log so you do that in below two steps:
  * Go to class level and add @Slf4j
  * And then add log.debug(..) at your required place
* No need to do this activity in 2 steps, only below 1 step is enough
  * Type log at you required place where you want to add log, IntelliJ will suggest you one option called "Lombok Slf4j" select that, which will automatically
    add @Slf4j annotation on class level for you so you just need to add log.debug(..)