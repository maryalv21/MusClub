import {  AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms";

export class CustomValidator {

static passwordLength(min: number, max: number): ValidatorFn {

  return (control: AbstractControl): ValidationErrors | null => {
      if (control.value.length < min || control.value.length > max) {
          return {
              passwordLength: true
          };
      }
      // todo OK
      return null;
  };
}

static noDigits(control: AbstractControl): ValidationErrors | null {

  // const regex = /[0-9]/;
  const regex = /\d/;

  if (control.value.match(regex)) {
      return {
          noDigits: true
      };
  }
  // todo OK
  return null;
}

static passwordMatch(group: AbstractControl): ValidationErrors | null {
  const password = group.get('password')?.value;
  const passwordConfirmation = group.get('passwordConfirmation')?.value;

  if (password !== passwordConfirmation) {
    // passwordConfirmation.markAsInvalid();
      return {
          passwordMatch: true
      };
  }
  // todo OK
  return null;
}
}
