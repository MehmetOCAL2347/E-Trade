export class CreateOrderRequest {
  constructor({
    name,
    surName,
    email,
    phoneNumber,
    province,
    district,
    address,
    paymentMethod,
    discountCode = "",
    cartItemList = [],
  }) {
    this.name = name;
    this.surName = surName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.province = province;
    this.district = district;
    this.address = address;
    this.paymentMethod = paymentMethod;
    this.discountCode = discountCode;
    this.cartItemList = cartItemList;
  }

  toRequestObject() {
    return {
      name: this.name,
      surName: this.surName,
      email: this.email,
      phoneNumber: this.phoneNumber,
      province: this.province,
      district: this.district,
      address: this.address,
      paymentMethod: this.paymentMethod,
      discountCode: this.discountCode,
      cartItemList: this.cartItemList,
    };
  }
}
