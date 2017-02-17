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


if __name__ == "__main__":
  play_with_multiple_line_string()
