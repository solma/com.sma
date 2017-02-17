import re

def _GetGoogle3RelPath(g3_path):
  prefix = 'google3/'
  assert g3_path[:len(prefix)] == prefix
  return g3_path[(g3_path.rfind(prefix) + len(prefix)):]


updated_importer_contents = re.sub(
  r'''^(\s*)import\s+['"](.*)(%s)['"](.*)$''' % _GetGoogle3RelPath(
    'google3/production/borg/topic-server-dev/e1.borg'),
  r'\1import "\2%s"\4' % _GetGoogle3RelPath('google3/production/borg/topic-server-dev/e2.borg'),
  '''
    import "//production/borg/topic-server-dev/e1.borg" as tmpl
  ''',
  flags=re.M)
print(updated_importer_contents)
