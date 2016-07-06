This library includes a custom lint rule to validate that when we have a base class (BaseResponse) implementing Parcelable the
subclasses include a Parcelable implementation.

Currently the detector is only checking that the class inheriting from BaseResponse has a CREATOR field.  
Also I’m adding in BaseResponse.writeToParcel method the annotation @CallSuper to enforce the call to super.writeToParcel from parent class
 
To register the Lint rule
Run the command 
gradle build install
 
This will copy the jar com.lania.android.products.project.lint.checkers-1.0.jar into .android\lint
 
After successfully registering the custom lint checker
 
Run the task in the project
gradle lint
And the rule will be validated  

