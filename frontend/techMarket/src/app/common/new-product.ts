export class NewProduct {
    constructor(
        public name: string,
        public description: string,
        public unitPrice: number,
        public imageUrl: string,
        public unitsInStock: number,
        public category: number
    ) { }
}
