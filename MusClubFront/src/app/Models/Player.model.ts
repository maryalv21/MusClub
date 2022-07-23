export class Player {

  constructor(
    private _name: string,
    private _playerName: string,
  ) {}
  public get playerName(): string {
    return this._playerName;
  }
  public set playerName(value: string) {
    this._playerName = value;
  }
  public get name(): string {
    return this._name;
  }
  public set name(value: string) {
    this._name = value;
  }
}
