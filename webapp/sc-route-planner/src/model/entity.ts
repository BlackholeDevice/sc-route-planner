class Entity {
  id: number;
  name: string;
  active: boolean;

  constructor(data?: any) {
    if(data) {
      Object.assign(this, data);
    }
  }
}
