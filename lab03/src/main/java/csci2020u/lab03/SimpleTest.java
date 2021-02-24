final String ALGORITHM_NAME = "HmacSHA256";
final String ENCODING_NAME = "UTF-8";

final String CLIENT_ID = "9c0d9e12-48fe-451a-87b2-827f11c46f12";
final String CLIENT_SECRET = "b88d0646-0d7f-4c6c-a3ff-39ea34ceb342";

String currentTime = Long.toString(System.currentTimeMillis() / 1000L);

/*
  If the request has a body, you would use this instead of empty string below (replace BODY with actual request body):

  MessageDigest digest = MessageDigest.getInstance("SHA-256");
  byte[] encodedHash = digest.digest("BODY".getBytes(StandardCharsets.UTF_8));
  String hashed_body = Hex.encodeHexString(encodedHash);
*/

String[] signatureParameters = {
    "GET",
    "",
    "/api/PATH",
    "",  //If the request has a BODY, this would be hashed_body
    currentTime
};

String signatureData = String.join(":", signatureParameters);

final Mac sha256_HMAC = Mac.getInstance(ALGORITHM_NAME);
final SecretKeySpec secret_key = new SecretKeySpec(CLIENT_SECRET.getBytes(ENCODING_NAME), ALGORITHM_NAME);
sha256_HMAC.init(secret_key);

byte[] computedSignature = sha256_HMAC.doFinal(signatureData.getBytes(ENCODING_NAME));

String NewtonAPIAuth = CLIENT_ID + ":" + Base64.getEncoder().encodeToString(computedSignature);
String NewtonDate = currentTime;