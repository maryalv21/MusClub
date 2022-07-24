import { User } from "./User.model";

export class Game {

  constructor(private _id: number, private _date: string,
    private _address: string, private _user: User){}

  public get user(): User {
    return this._user;
  }
  public set user(value: User) {
    this._user = value;
  }
  public get address(): string {
    return this._address;
  }
  public set address(value: string) {
    this._address = value;
  }
  public get date(): string {
    return this._date;
  }
  public set date(value: string) {
    this._date = value;
  }
  public get id(): number {
    return this._id;
  }
  public set id(value: number) {
    this._id = value;
  }

  public toJSON(){
    return {
      id: this.id,
      date: this._date,
      address: this._address,
      user: this.user
    }
  }
}
