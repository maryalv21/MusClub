import { User } from "./User.model";

export class Game {

    constructor(private _id: number, private _address: string,
    private _date: string,private _user: User[], private _playerName2: string,
    private _playerName3: string, private _playerName4: string){}
    
  public get playerName4(): string {
    return this._playerName4;
  }
  public set playerName4(value: string) {
    this._playerName4 = value;
  }
  public get playerName2(): string {
    return this._playerName2;
  }
  public set playerName2(value: string) {
    this._playerName2 = value;
  }
  public get playerName3(): string {
    return this._playerName3;
  }
  public set playerName3(value: string) {
    this._playerName3 = value;
  }

  public get user(): User[] {
    return this._user;
  }
  public set user(value: User[]) {
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
