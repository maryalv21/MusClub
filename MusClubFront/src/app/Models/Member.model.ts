export class Member {
    constructor(private _id: number, private _name: string, private _password: string,
    private _playerName: string, private _email: string, private _role: string,
    private _level: string, private _username: string) {}

  public get id(): number {
    return this._id;
  }
  public set id(value: number) {
    this._id = value;
  }

  public get username(): string {
    return this._username;
  }

  public set username(value: string) {
    this._username = value;
  }

  public get level(): string {
    return this._level;
  }
  public set level(value: string) {
    this._level = value;
  }

  public get role(): string {
    return this._role;
  }
  public set role(value: string) {
    this._role = value;
  }
  public get email(): string {
    return this._email;
  }
  public set email(value: string) {
    this._email = value;
  }
  public get playerName(): string {
    return this._playerName;
  }
  public set playerName(value: string) {
    this._playerName = value;
  }
  public get password(): string {
    return this._password;
  }
  public set password(value: string) {
    this._password = value;
  }
  public get name(): string {
    return this._name;
  }
  public set name(value: string) {
    this._name = value;
  }

  public toJSON(){
    return {
      id: this.id,
      name: this._name,
      playerName: this._playerName,
      email: this._email,
      password: this._password,
      role: this._role,
    }
  }

}
