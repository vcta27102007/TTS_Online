# TUẦN 6 - VALIDATION + EXCEPTION + API RESPONSE

## Đã làm
- Thêm validation cho TaskRequest bằng annotation
- Check deadline phải lớn hơn thời điểm hiện tại
- Hoàn thiện GlobalExceptionHandler
- Chuẩn hóa response theo ApiResponse
- Refactor TaskController, ProjectController, OrderController

## Validation chính
- `tieuDe`: không được để trống, tối đa 200 ký tự
- `moTa`: tối đa 1000 ký tự
- `deadline`: bắt buộc, phải ở tương lai
- `userId`, `projectId`: bắt buộc

## Exception mapping
- `BadRequestException` -> HTTP 400
- `NotFoundException` -> HTTP 404
- `MethodArgumentNotValidException` -> HTTP 400
- `HttpMessageNotReadableException` -> HTTP 400
- `Exception` -> HTTP 500

## Response chuẩn
```json
{
  "code": 200,
  "message": "Lấy danh sách task theo project thành công",
  "data": []
}
```

## Test Postman

- Tạo task hợp lệ → 201 Created
- Thiếu tiêu đề → 400 Bad Request
- Deadline quá khứ → 400 Bad Request
- User không tồn tại → 404 Not Found
- Project không tồn tại → 404 Not Found