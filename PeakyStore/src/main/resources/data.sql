INSERT INTO `user` VALUES
('894223f8-3e95-4483-b000-cae40e143d5b','1966-09-07','725.586.010-91','claudia@gmail.com',NULL,'claudia',_binary '','$2a$10$KoVoYPQmUWjRLeqHF9Y8C.mBezE7bELpuW0gxlH34G1TQ6XGc52bW'),
('ee66efd2-776b-48c2-9531-3f7ce55f651c','1998-09-13','134.386.936-66','igor@gmail.com',NULL,'igor',_binary '','$2a$10$yuwjQHR3YcBFY.GdrB1BbeqNytina5oKB2/HnICoycCLgKOxI521a');

INSERT INTO `role` VALUES
('0177b015-82c0-497a-8b60-4ef811b5c6e6','Client','894223f8-3e95-4483-b000-cae40e143d5b'),
('89681656-338b-47ec-95a4-c84acb687a58','Client','ee66efd2-776b-48c2-9531-3f7ce55f651c');

INSERT INTO `product` (`id`, `category`, `color`, `description`, `last_update_date`, `name`, `product_brand`, `purchase_price`, `sale_price`, `section`, `size`, `sku`, `stock_quantity`)
VALUES
('12e43d96-f0ad-43b7-82ec-d0c08280be66', '1', '2', 'Camisa bonita', '2022-10-26 10:00:00.000000', 'CAMISA ESPORTIVA', '2', '20.00', '30.00', '1', '2', '546546439', '200'),
('1eff9627-bc9b-4d48-93e5-e6ece7f965f5', '2', '3', 'Camisa feia', '2022-10-26 10:00:00.000000', 'CAMISA ESPORTIVA', '2', '20.00', '25.00', '2', '3', '546546438', '100');


