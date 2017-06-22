def play_with_multiple_line_string():
  conf = """
    BqLoad  {
        projectId="ups-dev"
        datasetId="solma_udf_test"
        tableId="guru_bq_test"
        gcsFiles="gs://ups_udf/foo.txt gs://ups_udf/foo1.txt"
        gcsFileSchema="model:String country_code:String area_code:String area_name:String device_cnt:String"
    }
    """
  multiple_line_without_triple_quotes = ("line1, "
                                         "line2")
  print(multiple_line_without_triple_quotes)
  # print([line for line in conf.splitlines() if '{' in line][0].split('{')[0].strip())

def encode():
  raw_iteral = u'你好，世界'
  raw_iteral_in_unicode = raw_iteral.encode('utf-8')
  escaped_iteral_in_unicode = repr(raw_iteral_in_unicode)
  for s in [raw_iteral, raw_iteral_in_unicode, escaped_iteral_in_unicode]:
    print(s)

if __name__ == "__main__":
  # play_with_multiple_line_string()
  encode()
